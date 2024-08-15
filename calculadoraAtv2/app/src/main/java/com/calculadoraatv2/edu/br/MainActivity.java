package com.calculadoraatv2.edu.br;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    private Calculadora calculadora;
    private EditText visor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculadora = new Calculadora();
        visor = findViewById(R.id.visor);

        configurarBotoes();
    }

    private void configurarBotoes() {

        int[] idsNumeros = {R.id.btnZero, R.id.btnUm, R.id.btnDois, R.id.btnTres, R.id.btnQuatro, R.id.btnCinco, R.id.btnSeis, R.id.btnSete, R.id.btnOito, R.id.btnNove};
        for (int i = 0; i < idsNumeros.length; i++) {
            final int num = i;
            findViewById(idsNumeros[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adicionarNumero(num);
                }
            });
        }


        findViewById(R.id.btnMais).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculadora.soma();
                atualizarVisor();
            }
        });

        findViewById(R.id.btnMenos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculadora.subtracao();
                atualizarVisor();
            }
        });

        findViewById(R.id.btnMulti).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculadora.multiplicacao();
                atualizarVisor();
            }
        });

        findViewById(R.id.btnDiv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculadora.divisao();
                atualizarVisor();
            }
        });


        findViewById(R.id.btnEnter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculadora.enter();
                atualizarVisor();
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculadora = new Calculadora();
                atualizarVisor();
            }
        });

        findViewById(R.id.btnDel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarUltimoDigito();
            }
        });

        findViewById(R.id.btnVirgula).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarVirgula();
            }
        });
    }

    private void adicionarNumero(int numero) {
        if (calculadora.getModo() == Calculadora.MODO_EXIBINDO) {
            visor.setText("");
            calculadora.setNumero(0);
        }
        String textoVisor = visor.getText().toString();
        textoVisor += numero;
        visor.setText(textoVisor);
        calculadora.setNumero(Double.parseDouble(textoVisor));
    }

    private void adicionarVirgula() {
        String textoVisor = visor.getText().toString();
        if (!textoVisor.contains(".")) {
            textoVisor += ".";
            visor.setText(textoVisor);
        }
    }

    private void deletarUltimoDigito() {
        String textoVisor = visor.getText().toString();
        if (textoVisor.length() > 0) {
            textoVisor = textoVisor.substring(0, textoVisor.length() - 1);
            visor.setText(textoVisor);
            if (!textoVisor.isEmpty()) {
                calculadora.setNumero(Double.parseDouble(textoVisor));
            } else {
                calculadora.setNumero(0);
            }
        }
    }

    private void atualizarVisor() {
        if (calculadora.getModo() == Calculadora.MODO_ERRO) {
            visor.setText("Erro");
        } else {
            visor.setText(String.valueOf(calculadora.getNumero()));
        }
    }

}