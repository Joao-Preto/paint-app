package com.example.paint

import android.os.Bundle
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PaintZone.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaintZone : Fragment() {
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
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val c = container?.context
        val gl = GestureListener()
        val gd = GestureDetector(c, gl)
        gd.setIsLongpressEnabled(true)
        gd.setOnDoubleTapListener(gl)

        gl.setFragmentManager(parentFragmentManager)

        canvas = CanvasView(c!!)
        gl.setCanvas(canvas)
        canvas.setGestureDetector(gd)
        this.parentFragmentManager.setFragmentResultListener(
            Extra_PAINT,////////////////////////////////////////////////////////////////////////////////////////////////////
            viewLifecycleOwner,
            { requestKey, result ->
                when (requestKey) {
                    Extra_PAINT -> {
                        val newPaintColor= result.getInt(Extra_PAINT)
                        canvas.changePaintColor(newPaintColor)
                        canvas.invalidate()
                    }
                    EXTRA_COLOR -> {
                        val newBackgroundColor = result.getInt(EXTRA_COLOR)
                        canvas.changeBackgroundColor(newBackgroundColor)
                        canvas.invalidate()
                    }
                    Extra_SHAKE -> {
                        Toast.makeText(context, "shake", Toast.LENGTH_SHORT)
                        canvas.erase()
                        canvas.invalidate()
                    }
                }
            }
        )
        return canvas // inflater.inflate(R.layout.fragment_paint_zone, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PaintZone.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaintZone().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}