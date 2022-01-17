package com.example.yana.kinopoiskhome.ui.treiler

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import androidx.core.view.isVisible
import com.example.yana.kinopoiskhome.data.filmTriller.Items
import com.example.yana.kinopoiskhome.databinding.ActivityTreilerBinding
import com.example.yana.kinopoiskhome.ui.details.KinoInfoActivity
import com.example.yana.kinopoiskhome.ui.details.KinoInfoActivity.Companion.FILM_ID
import com.example.yana.kinopoiskhome.utils.MediaPlayer
import org.koin.android.ext.android.inject

class TreilerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTreilerBinding
    private val treiler by lazy { intent.getParcelableExtra(TREILER) as? Items }
    private val playerContract: MediaPlayer by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreilerBinding.inflate(layoutInflater)
        setContentView(binding.root)

       if (treiler?.site != "UNKNOWN"){
           setupWebView()
           binding.exoPlayer.isVisible = false
           binding.treilerWeb.isVisible = true
       }else{binding.exoPlayer.isVisible = true
           binding.treilerWeb.isVisible = false
           setupExoPlayer()
       }
    }

    private fun setupExoPlayer(){
        val playerTr = playerContract.init(this)
        binding.exoPlayer.player = playerTr
        playerContract.play(
            treiler?.url ?: "",
            this
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        playerContract.stop()
    }


    private fun setupWebView(){
        binding.treilerWeb.webViewClient = object: WebViewClient(){
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
            }
        }
        binding.treilerWeb.settings.javaScriptEnabled = true
        binding.treilerWeb.loadUrl(treiler?.url ?: "")
    }

    companion object{
        const val TREILER = "treiler"
        fun open(context: Context, items: Items) {
            val intent = Intent(context, TreilerActivity::class.java)
            intent.putExtra(TREILER, items)
            context.startActivity(intent)
        }
    }
}