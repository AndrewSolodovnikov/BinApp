package com.sol.binapp.ui.binHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sol.binapp.data.BinWithNumber
import com.sol.binapp.databinding.FragmentBinHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BinHistoryFragment : Fragment() {
    private val binding by lazy { FragmentBinHistoryBinding.inflate(layoutInflater) }
    private lateinit var binAdapter: BinHistoryAdapter
    private val viewModel: BinHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binAdapter = BinHistoryAdapter(emptyList())
        binding.rvBin.adapter = binAdapter
        binding.rvBin.layoutManager = LinearLayoutManager(requireContext())

        viewModel.loadBinHistory()

        binAdapter.setOnItemClickListener(object : BinHistoryAdapter.OnItemClickListener {
            override fun onItemClick(bin: BinWithNumber) {
                val binNumber = bin.number.number
                openBinByBinId(binNumber)
            }

            private fun openBinByBinId(binNumber: String) {
                findNavController().navigate(
                    BinHistoryFragmentDirections.actionBinHistoryFragmentToBinBankInfoFragment(
                        binNumber
                    )
                )
            }
        })

        viewModel.binHistoryState.observe(viewLifecycleOwner) { state ->
            if (state.binList.isEmpty()) {
                binding.rvBin.visibility = View.GONE
                binding.tvEmptyPlaceholderHistory.visibility = View.VISIBLE
            } else {
                binding.rvBin.visibility = View.VISIBLE
                binding.tvEmptyPlaceholderHistory.visibility = View.GONE
                binAdapter.updateBinList(state.binList)
            }
        }
    }
}
