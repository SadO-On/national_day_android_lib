package studio.s98.game_tab_demo.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


fun shareImage(resId: Int, context: Context) {
    val bitmap = BitmapFactory.decodeResource(context.resources, resId)
    val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        .toString() + "/Share.png"
    val out: OutputStream?
    val file = File(path)
    try {
        out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        out.flush()
        out.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    val uri = FileProvider.getUriForFile(
        context,
        context.packageName + ".provider",
        file
    )

    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
    shareIntent.setType("image/png")
    context.startActivity(Intent.createChooser(shareIntent, "Share with"))
}

