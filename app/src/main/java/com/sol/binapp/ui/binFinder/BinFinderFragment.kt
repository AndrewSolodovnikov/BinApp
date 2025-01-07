package com.sol.binapp.ui.binFinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sol.binapp.R
import com.sol.binapp.databinding.FragmentBinFinderBinding

class BinFinderFragment : Fragment() {
    private val binding by lazy { FragmentBinFinderBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.btnSendBin.setOnClickListener {
            val binNumber: String = binding.etDigit.text.toString().trim()
            if (binNumber.length in 6..8) {
                findNavController().navigate(
                    BinFinderFragmentDirections.actionBinFinderFragmentToBinBankInfoFragment(binNumber)
                )
            } else {
                errorBinLengthToast()
            }
        }
    }

    private fun errorBinLengthToast() {
        Toast.makeText(context, R.string.error_toast_find_bind, Toast.LENGTH_LONG).show()
    }
}
