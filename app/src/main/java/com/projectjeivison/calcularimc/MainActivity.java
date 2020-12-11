package com.projectjeivison.calcularimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

        private EditText editeIdade;
        private EditText editeAltura;
        private EditText editePeso;
        private RadioGroup genero;
        private RadioButton Masculino;
        private RadioButton Feminino;
        private TextView Resultado;
        private Button Clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RECUPERAR DADOS
        editeIdade  = findViewById(R.id.editTextIdade);
        editeAltura = findViewById(R.id.textAltura);
        editePeso   = findViewById(R.id.textPeso);

        //RADIO GROUP
        genero = findViewById(R.id.opcaoSexo);
        Masculino = findViewById(R.id.radioButtonM);
        Feminino = findViewById(R.id.radioButtonF);

        //RESULTADO
          Resultado   = findViewById(R.id.textViewResultado);

          //limpar campos
            Clear = findViewById(R.id.limparT);

        Clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                limpar(v);
            }
        });

        radiobutton();
    }

    public void limpar(View v){
        editeIdade.setText("");
        editeAltura.setText("");
        editePeso.setText("");
        Resultado.setText("");
    }

    public void CalcularIMC(View view) {
        radiobutton();
        //recuperar valores digitados
        String Didade = editeIdade.getText().toString();
        String Daltura = editeAltura.getText().toString();
        String Dpeso = editePeso.getText().toString();

        Boolean camposValidados = validarCampos(Didade, Daltura, Dpeso);
        if (camposValidados) {

            //Convertendo string para números
            int idade = Integer.parseInt(Didade);
            double altura = Double.parseDouble(Daltura);
            double peso = Double.parseDouble(Dpeso);

            // variaveis
            double ideal = altura * altura;
            double resultado = peso / ideal;

            //ARREDONDAR E DIMINUIR O NÚMERO DE CASAS DECIMAIS EM JAVA
            DecimalFormat formatador = new DecimalFormat("00.00");

            if (resultado <= 18.5) {
                Resultado.setText("Idade : " + idade + "\nSeu IMC é: " + formatador.format(resultado) +
                        "\nMagreza: \nQuando o resultado é menor que 18,5 kg/m2");
            }
            if (resultado >= 18.5 || resultado <= 24.9) {
                Resultado.setText("Idade : " + idade + "\nSeu IMC é: " + formatador.format(resultado) +
                        "\nNormal: \nQuando o resultado está entre 18,5 e 24,9 kg/m2");
            }
            if (resultado >= 24.9 || resultado <= 30) {
                Resultado.setText("Idade : " + idade + "\nSeu IMC é: " + formatador.format(resultado) +
                        "\nSobrepeso: \nQuando o resultado está entre 24,9 e 30 kg/m2");
            }
            if (resultado >= 30) {
                Resultado.setText("Idade : " + idade + "\n Seu IMC é: " + formatador.format(resultado) +
                        "\n Obesidade: \nQuando o resultado é maior que 30 kg/m2");
            }
        }else {
            Resultado.setText("Preencha os dados primeiro!");
        }

    }

        //VALIDAR CAMPOS: CONFIRMAR SE O USUARIO DIGITOU ALGO ANTES DE CALCULAR
        public Boolean validarCampos(String rIdade, String rPeso, String rAltura){
            Boolean camposValidados = true;

            if (rIdade == null || rIdade.equals("")){
                camposValidados = false;
            }if (rAltura == null || rAltura.equals("")){
                camposValidados = false;
            }if (rPeso == null || rPeso.equals("")){
                camposValidados = false;
            }
            return camposValidados;
        }

    public String radiobutton(){
        genero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radioButtonM){
                    Resultado.setText("Masculino");
                }else if (checkedId == R.id.radioButtonF){
                    Resultado.setText("Feminino");
                }
            }
        });

    }

}

//By Jeivison Santos







