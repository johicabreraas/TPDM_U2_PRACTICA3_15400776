package ittepic.edu.mx.tpdm_u2_practica3_15400776

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var numeroN: EditText? = null
    var numeroM: EditText? = null

    var etiqueta: TextView? = null

    var Generar: Button? = null
    var Primos: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numeroN = findViewById(R.id.num1)
        numeroM = findViewById(R.id.num2)
        etiqueta = findViewById(R.id.Mostrar)
        Generar = findViewById(R.id.botonG)
        Primos = findViewById(R.id.botonP)

        Generar?.setOnClickListener{
            etiqueta?.setText("")
            GenerarNumeros(numeroN?.text.toString(), numeroM?.text.toString(),applicationContext).execute()
        }

        Primos?.setOnClickListener{
            val abrirArchivo = BufferedReader(InputStreamReader(openFileInput("numeros.txt")))
            var cadena = ""
            cadena = abrirArchivo.readLine()
            etiqueta?.setText(cadena)
        }
    }

    class GenerarNumeros(numeroN: String, numeroM: String, context: Context) : AsyncTask<Void, Void, List<Int>>(){ //aqui es donde Inicia la clase AsyncTask
        var numN = numeroN.toInt()
        var numM = numeroM.toInt()
        var context = context



        override fun doInBackground(vararg p0: Void?): List<Int>? { //se ejecuta en segundo plano
            val generanumeros = List(2000){ Random.nextInt(numN,numM)}
            println(generanumeros)

            return generanumeros
        }

        override fun onPostExecute(result: List<Int>?) {
            super.onPostExecute(result)
            var contador = 0
            var ne=""
            var numerosPrimos="números primos encontrados: "
            (0..1999).forEach {
                contador=0
                ne=result?.get(it).toString()
                (1..ne.toInt()).forEach {
                    if (ne.toInt() % it == 0) {
                        contador++
                    }
                }
                if (contador <= 2 && ne.toInt()>1) {
                    numerosPrimos+= ne+ ", "
                } else {  }
            }
            println(numerosPrimos)
            guardar(numerosPrimos)

        }

        fun guardar(numerosPrimos:String){
            val archivo = OutputStreamWriter(context.openFileOutput("numeros.txt", Activity.MODE_PRIVATE))
            archivo.write(numerosPrimos)
            archivo.flush()
            archivo.close()
            Toast.makeText(context,"Numeros Guardados Correctamente",Toast.LENGTH_LONG).show()
        }
        fun validarv() {
        }
    }
    }


