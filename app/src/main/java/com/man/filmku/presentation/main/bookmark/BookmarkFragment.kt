package com.man.filmku.presentation.main.bookmark

import androidx.fragment.app.viewModels
import com.man.filmku.base.BaseFragment
import com.man.filmku.databinding.FragmentBookmarkBinding
import com.man.filmku.presentation.main.adapter.AdapterPopular
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(
    FragmentBookmarkBinding::inflate
) {

    val viewModel : BookmarkViewModel by viewModels()
    override fun onViewReady() {

        val adapter = AdapterPopular()
        binding.rvPopular.adapter = adapter

        viewModel.bookmarkMovie.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.getBookmark()

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment BookmarkFragment.
         */
        @JvmStatic
        fun newInstance() =
            BookmarkFragment().apply {}
    }
}