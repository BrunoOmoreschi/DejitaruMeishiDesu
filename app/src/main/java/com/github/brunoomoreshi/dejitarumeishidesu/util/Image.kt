package com.github.brunoomoreshi.dejitarumeishidesu.util
//Artefato para geração das imagens a serem compartilhadas dos cartões de visita.

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.github.brunoomoreshi.dejitarumeishidesu.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream



class Image {

    //Tem que estar dentro de um companion obj para ser acessivel.
    //Assim não é necessário ficar instanciando a função.
    companion object{

        fun share (context: Context, view: View){

            //Pega imagem da activity
            val bitmap = getScreenShareFromView(view)

            //Sala a img.
            bitmap?.let {
                saveMediaToStorage(context, bitmap)
            }
        }

        private fun getScreenShareFromView (view: View): Bitmap?{
            var screenshot: Bitmap? = null
            try {
                screenshot =
                    Bitmap.createBitmap(
                        view.measuredWidth,
                        view.measuredHeight,
                        Bitmap.Config.ARGB_8888
                    )
            }catch (e: Exception) {
                Log.e("Dev", "Não foi possivel tirar um print pq:" + e.message)
            }
            return screenshot
        }

        private fun saveMediaToStorage(context: Context, bitmap: Bitmap) {
            val filename = "${System.currentTimeMillis()}.jpg"

            //File output stream
            var fos: OutputStream? = null

            //Pode ter problemas pois outras versões diferentes da Q funcionam diferente... então tem que criar um método diferente para cada versão...
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                context.contentResolver?.also { resolver ->
                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                        //Seta o tipo do arquivo a ser salvo.
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }
                    val imageUri: Uri? =
                        (resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues))

                    fos = imageUri?.let {
                        shareIntent(context, imageUri)
                        resolver.openOutputStream(it)
                    }
                }
            } else {
                // These for devices running on android < Q
                val imagesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val image = File(imagesDir, filename)
                shareIntent(context, Uri.fromFile(image))
                fos = FileOutputStream(image)
            }

            fos?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                Toast.makeText(context, context.getString(R.string.toastImgSuccess_txt), Toast.LENGTH_SHORT).show()
            }
        }

        //Abre a lista de apps instalados para selecionar o compatilhamento.
        private fun shareIntent(context: Context, image: Uri) {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, image)
                type = "image/jpeg"
            }
            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    "Compartilhar"
                )
            )
        }

    }


}