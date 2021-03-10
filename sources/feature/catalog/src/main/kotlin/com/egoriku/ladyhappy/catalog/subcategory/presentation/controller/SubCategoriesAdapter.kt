package com.egoriku.ladyhappy.catalog.subcategory.presentation.controller

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.databinding.AdapterItemSubcategoryBinding
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.core.adapter.BaseListAdapter
import com.egoriku.ladyhappy.core.adapter.BaseViewHolder
import com.egoriku.ladyhappy.extensions.*
import kotlin.properties.Delegates

private const val CROSSFADE_DURATION = 100

class SubCategoriesAdapter(
        private val onCatalogItemClick: (item: SubCategoryItem) -> Unit,
        private val onTrendingClick: (view: View) -> Unit,
        private val onLongPressListener: (reference: String) -> Unit
) : BaseListAdapter<SubCategoryItem, SubCategoriesAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ) = VH(AdapterItemSubcategoryBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(
            holder: VH,
            position: Int,
            model: SubCategoryItem
    ) = holder.bind(model)

    inner class VH(
            private val binding: AdapterItemSubcategoryBinding
    ) : BaseViewHolder<SubCategoryItem>(binding.root) {

        private var subCategoryItem: SubCategoryItem by Delegates.notNull()

        init {
            binding.mozaikLayout.onViewReady = { view, url ->
                Glide.with(itemView.context)
                        .load(url)
                        .transition(DrawableTransitionOptions.withCrossFade(CROSSFADE_DURATION))
                        .into(view)
            }

            itemView.setOnClickListener {
                onCatalogItemClick(subCategoryItem)
            }

            itemView.setOnLongClickListener {
                consume {
                    onLongPressListener.invoke(subCategoryItem.documentReference)
                }
            }

            binding.trending.setOnClickListener {
                onTrendingClick(it)
            }
        }

        override fun bind(item: SubCategoryItem) {
            subCategoryItem = item

            binding.bind(item)
        }

        private fun AdapterItemSubcategoryBinding.bind(data: SubCategoryItem) {
            if (data.images.isNotEmpty()) {
                cardView.visible()
                mozaikLayout.setItems(data.images)

                when {
                    data.isPopular -> trending.visible()
                    else -> trending.gone()
                }
            } else {
                cardView.gone()
            }

            subCategoryTitle.text = data.name
            subCategorySize.text = context.getQuantityStringZero(
                    pluralResId = R.plurals.catalog_images_count,
                    zeroResId = R.string.catalog_images_count_zero,
                    quantity = data.publishedCount
            )
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<SubCategoryItem>() {

        override fun areItemsTheSame(oldItem: SubCategoryItem, newItem: SubCategoryItem) = oldItem == newItem

        override fun areContentsTheSame(oldItem: SubCategoryItem, newItem: SubCategoryItem) = oldItem.subCategoryId == newItem.subCategoryId
    }
}