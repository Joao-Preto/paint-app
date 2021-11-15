package com.example.paint

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.core.os.bundleOf
import com.example.paint.R.drawable.ic_tick_done

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var pickedColor = Color.WHITE

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
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val view = root.rootView
        if (view != null) {
            view.findViewById<ImageButton>(R.id.blackColorButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.whiteColorButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.redColorButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.orangeColorButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.yellowColorButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.lightGreenColorButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.greenColorButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.aquaColorButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.blueColorButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.darkPurpleColorButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.purpleColorButton).setOnClickListener(this)
            view.findViewById<ImageButton>(R.id.magentaColorButton).setOnClickListener(this)
            view.findViewById<Button>(R.id.pickColorButton).setOnClickListener(this)
            view.findViewById<Button>(R.id.button5).setOnClickListener(this)
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
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when(view.id) {
                R.id.blackColorButton      -> {
                    pickedColor = Color.BLACK
                }
                R.id.whiteColorButton      -> {
                    pickedColor = Color.WHITE
                    view.setBackgroundResource(R.drawable.ic_tick_done)
                }
                R.id.redColorButton        -> pickedColor = Color.RED
                R.id.orangeColorButton     -> pickedColor = Color.rgb(255,144,0)
                R.id.yellowColorButton     -> pickedColor = Color.YELLOW
                R.id.lightGreenColorButton -> pickedColor = Color.rgb(160,255,0)
                R.id.greenColorButton      -> pickedColor = Color.GREEN
                R.id.aquaColorButton       -> pickedColor = Color.rgb(0,255,200)
                R.id.blueColorButton       -> pickedColor = Color.BLUE
                R.id.darkPurpleColorButton -> pickedColor = Color.rgb(55,0,255)
                R.id.purpleColorButton     -> pickedColor = Color.rgb(155,0,255)
                R.id.magentaColorButton    -> pickedColor = Color.rgb(255,0,255)
                R.id.pickColorButton       -> parentFragmentManager.setFragmentResult(EXTRA_COLOR, bundleOf(EXTRA_COLOR to pickedColor))
                R.id.button5               -> closeSettings()

            }
        }
    }

    fun closeSettings() {
        parentFragmentManager.popBackStack()
//        parentFragmentManager.beginTransaction().detach(this).commit()
    }
}