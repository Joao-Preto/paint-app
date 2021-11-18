package com.example.paint

import android.os.Bundle
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [PaintZone.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaintZone : Fragment() {
    private lateinit var canvas: CanvasView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
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
            Extra_PAINT,
            viewLifecycleOwner,
            { _, result ->
                val newPaintColor= result.getInt(Extra_PAINT)
                canvas.changePaintColor(newPaintColor)
            }
        )
        this.parentFragmentManager.setFragmentResultListener(
            Extra_SHAKE,
            viewLifecycleOwner,
            { _, _ -> canvas.erase() }
        )
        this.parentFragmentManager.setFragmentResultListener(
            EXTRA_ERASE,
            viewLifecycleOwner,
            { _, _ -> canvas.erase() }
        )
        this.parentFragmentManager.setFragmentResultListener(
            EXTRA_UNDO,
            viewLifecycleOwner,
            { _, _ -> canvas.undo() }
        )
        this.parentFragmentManager.setFragmentResultListener(
            EXTRA_SAVE,
            viewLifecycleOwner,
            { _, result ->
                Toast.makeText(context, "saved?", Toast.LENGTH_SHORT).show()
                val name: String = result.getString(EXTRA_SAVE).toString()
                canvas.saveDrawing(name)
            }
        )
        return canvas // inflater.inflate(R.layout.fragment_paint_zone, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PaintZone.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            PaintZone().apply {
                arguments = Bundle().apply {
                }
            }
    }
}