package com.yeho.holamundo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yeho.holamundo.R
import com.yeho.holamundo.data.RepoResult
import com.yeho.holamundo.databinding.ItemRepoBinding
import com.yeho.holamundo.extensions.ctx

private lateinit var binding: ItemRepoBinding
private lateinit var context: Context

class RepoListAdapter(private val repoList: RepoResult) : RecyclerView.Adapter<RepoListAdapter.ItemsRepoViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsRepoViewHolder {
    context = parent.ctx
    binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.ctx), parent, false)
    return ItemsRepoViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ItemsRepoViewHolder, position: Int) {
    with(holder){
      with(repoList.items[position]) {
        binding.username.text = this.owner.login.orEmpty()
        binding.repoName.text = this.fullName.orEmpty()
        binding.repoDescription.text = this.description.orEmpty()

        Glide.with(context).load(this.owner.avatarUrl).centerCrop()
          .placeholder(R.drawable.ic_launcher_foreground)
          .into(binding.icon);
      }
    }
  }

  override fun getItemCount(): Int = repoList.items.size

  inner class ItemsRepoViewHolder(binding: ItemRepoBinding) :RecyclerView.ViewHolder(binding.root)

}