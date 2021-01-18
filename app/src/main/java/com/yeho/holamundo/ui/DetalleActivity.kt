package com.yeho.holamundo.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeho.holamundo.R
import com.yeho.holamundo.api.RepositoryRetriever
import com.yeho.holamundo.databinding.ActivityDetalleBinding
import com.yeho.holamundo.ui.adapters.RepoListAdapter
import kotlinx.coroutines.*

private lateinit var binding: ActivityDetalleBinding
private lateinit var  context: Context


class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        context = this

        binding.rvRepo.layoutManager = LinearLayoutManager(this)

        if (isNetworkConnected()) {
            retrieveRepositories()
        } else {
            AlertDialog.Builder(this).setTitle(getString(R.string.txt_title_no_internet))
                .setMessage(getString(R.string.txt_msg_no_internet))
                .setPositiveButton(android.R.string.ok) { _, _ -> goPreviousActivity()}
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    fun retrieveRepositories() {
        val mainActivityJob = Job()
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(this).setTitle(getString(R.string.txt_error))
                .setMessage(exception.message)
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            val resultList = RepositoryRetriever().getRepositories()
            binding.rvRepo.adapter = RepoListAdapter(resultList)
        }
    }

    private fun goPreviousActivity() {
    finish()
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null
                && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}