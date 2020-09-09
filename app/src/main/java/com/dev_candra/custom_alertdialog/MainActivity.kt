package com.dev_candra.custom_alertdialog

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewGroupCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_layou.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Aksi()
    }

    private fun Aksi(){
        btn1.apply {
            setOnClickListener {
                makeLayout(16f,16f,"Masukkan nama anda","Masukkan email anda"
                ,50,50,40,10,"Yes","Cancel",
                "Membuat Custom Dialog")
            }
        }

        btn2.apply {
            setOnClickListener {
                customLayoutKeDua()
            }
        }

        actionBar()
    }

    // membuat layout secara programatically kotlin
    // logika
    /*
    1. baut dulu alert dialog
    2. buat layout dialog
    3. buat widgetnya lalu masukkan kedalam layout
    4. masukkan layout tadi kedalam alertdialog
    5.layout tadi buat childnya
    6. buat padding dari layout tadi
     */
    private fun makeLayout(setOne : Float,setTwo : Float,setEditName : String, setEditEmail : String,setPaddingLeft : Int,
        setPaddingRight : Int, setPaddingTop : Int, setPaddingBottom : Int, postive : String, negative : String,judul : String
    ){
        val builder1 = AlertDialog.Builder(this@MainActivity)
        val mLayout = LinearLayout(this@MainActivity)
        val mTvName = TextView(this@MainActivity)
        val tvEmail = TextView(this@MainActivity)
        val nEtName = EditText(this@MainActivity)
        val EtEmail = EditText(this@MainActivity)

        mTvName.text = "Enter your name"
        tvEmail.text = "Enter your email"

        mTvName.setTextSize(TypedValue.COMPLEX_UNIT_SP,setOne)
        tvEmail.setTextSize(TypedValue.COMPLEX_UNIT_SP,setTwo)
        nEtName.setSingleLine()
        nEtName.hint = setEditName
        EtEmail.setSingleLine()
        EtEmail.hint = setEditEmail
        mLayout.orientation = LinearLayout.VERTICAL
        mLayout.setBackgroundColor(Color.RED)
        mLayout.addView(mTvName)
        mLayout.addView(nEtName)
        mLayout.addView(tvEmail)
        mLayout.addView(EtEmail)
        mLayout.setPadding(setPaddingLeft,setPaddingTop,setPaddingRight,setPaddingBottom)
        builder1.setView(mLayout)
        builder1.setMessage(judul.toUpperCase())
        builder1.setPositiveButton(postive){
            dialog: DialogInterface?, which: Int ->
            val name = nEtName.text.toString()
            val email = EtEmail.text.toString()
            teks1.apply {
                text = "Name: " + name + "\n Email: " + email
            }
        }
        // set neutral button
        builder1.setNeutralButton(negative){
            dialog: DialogInterface?, which: Int ->
            dialog?.dismiss()
        }
        builder1.create().show()

    }

    // membuat inisasi toolbar
    private fun actionBar(){
        val initToolbar = supportActionBar
        if (initToolbar != null){
            initToolbar.title = "Candra Julius Sinaga"
            initToolbar.subtitle = "Contoh Custom AlertDialog"
        }
    }

    /*
    1. buat dulu layout dialognya
    2. masukkan kedalam alertdialog
    3. buat sebuah variabel baru lalu masukkan dialog tadi panggil function show
    4. gunakan wiget yang ada di layout tadi
     */
    private fun customLayoutKeDua(){
       val layoutDialog = LayoutInflater.from(this).inflate(R.layout.custom_layou,null)
        val dialog = AlertDialog.Builder(this)
            .setView(layoutDialog)
            .setIcon(R.drawable.ic_launcher_background)
            .setMessage("Dialog Input User")
        val dialog2 = dialog.show()
        dialog2.button_ok.setOnClickListener {
            dialog2.dismiss()
            val name = dialog2.eidtText1.text.toString().toLowerCase()
            val email = dialog2.eidtText2.text.toString().toLowerCase()
            val password = dialog2.eidtText3.text.toString().toLowerCase()
            text2.apply {
                setText("Name: $name\n Email: $email\n Password: $password")
                visibility = View.VISIBLE
            }
        }
        dialog2.button_cancel.setOnClickListener {
            dialog2.dismiss()
            text2.apply {
                visibility = View.GONE
            }
        }
    }

    private fun aksi1(){
        val cutsomDialog = LayoutInflater.from(this).inflate(R.layout.custom_layou,null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(cutsomDialog)
            .setTitle("Form nama")
        val builderDialog = mBuilder.show()
        builderDialog.button_ok.setOnClickListener {
            builderDialog.dismiss()
        }
        val name = builderDialog.eidtText1.text.toString()
        val email = builderDialog.eidtText2.text.toString()
        val password = builderDialog.eidtText3.text.toString()
        text2.apply {
            visibility = View.VISIBLE
            setText("Name: " + name + "\n Email: " + email + "\n Password: " + password)
        }
        builderDialog.button_cancel.setOnClickListener {
            builderDialog.dismiss()
            text2.apply {
                visibility = View.GONE
            }
        }
    }
}
