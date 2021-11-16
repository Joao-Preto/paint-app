package com.example.paint

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PaletteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaletteFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var canvas: CanvasView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_palette, container, false)
        val view = root.rootView
        if (view != null) {
            view.findViewById<ImageButton>(R.id.copyButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.brushButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.eraseButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.undoButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.copyButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.pasteButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.blackPaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.whitePaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.redPaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.orangePaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.yellowPaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.lightGreenPaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.darkGreenPaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.aquaPaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.bluePaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.darkPurplePaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.purplePaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.magentaPaintButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.eraseButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.undoButton).setOnClickListener(this)
        }
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PaletteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaletteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun setCanvas (can:CanvasView) {
        canvas = can
    }

    override fun onClick(view: View?) {
        var result = Color.BLACK
        if (view != null) {
            when (view.id) {
                R.id.blackPaintButton      -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.BLACK))
                R.id.whitePaintButton      -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.WHITE ))
                R.id.redPaintButton        -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.RED))
                R.id.orangePaintButton     -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.rgb(255,144,0)))
                R.id.yellowPaintButton     -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.YELLOW))
                R.id.darkGreenPaintButton  -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.GREEN))
                R.id.aquaPaintButton       -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.rgb(0,255,200)))
                R.id.lightGreenPaintButton -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.rgb(160,255,0)))
                R.id.bluePaintButton       -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.BLUE))
                R.id.darkPurplePaintButton -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.rgb(55,0,255)))
                R.id.purplePaintButton     -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.rgb(155,0,255)))
                R.id.magentaPaintButton    -> parentFragmentManager.setFragmentResult(Extra_PAINT,
                    bundleOf(Extra_PAINT to Color.rgb(255,0,255)))
                R.id.eraseButton           -> parentFragmentManager.setFragmentResult(EXTRA_ERASE,
                    bundleOf())
                R.id.undoButton            -> parentFragmentManager.setFragmentResult(EXTRA_UNDO,
                    bundleOf())
            }
        }
    }
}