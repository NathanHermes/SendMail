package br.edu.ifsp.scl.pdm.sendmail

import android.content.Intent
import android.content.Intent.ACTION_CHOOSER
import android.content.Intent.ACTION_SEND
import android.content.Intent.ACTION_SENDTO
import android.content.Intent.EXTRA_BCC
import android.content.Intent.EXTRA_CC
import android.content.Intent.EXTRA_EMAIL
import android.content.Intent.EXTRA_INTENT
import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.EXTRA_TEXT
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.pdm.sendmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        with(amb) {
            cleanBt.setOnClickListener {
                toEt.setText("")
                ccEt.setText("")
                bccEt.setText("")
                subjectEt.setText("")
                messageEt.setText("")
            }

            sendBt.setOnClickListener {
                val sendMailIntent = Intent(ACTION_SENDTO)

                with(sendMailIntent) {
                    putExtra(EXTRA_EMAIL, arrayOf(toEt.text.toString()))
                    putExtra(EXTRA_CC, arrayOf(ccEt.text.toString()))
                    putExtra(EXTRA_BCC, arrayOf(bccEt.text.toString()))
                    putExtra(EXTRA_SUBJECT, arrayOf(subjectEt.text.toString()))
                    putExtra(EXTRA_TEXT, arrayOf(messageEt.text.toString()))
                    type = "message/rfc822"
                    data = Uri.parse("mailto:")
                }

                val chooserIntent = Intent(ACTION_CHOOSER)
                chooserIntent.putExtra(EXTRA_INTENT, sendMailIntent)
                startActivity(chooserIntent)
            }
        }
    }
}