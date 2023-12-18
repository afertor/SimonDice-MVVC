package com.dam.simondice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

public class SimonDiceActivity extends AppCompatActivity {
    private SimonDiceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon_dice);

        viewModel = new ViewModelProvider(this).get(SimonDiceViewModel.class);

        Button startButton = findViewById(R.id.btnShowSequence);
        Button redButton = findViewById(R.id.btnRed);
        Button greenButton = findViewById(R.id.btnGreen);
        Button blueButton = findViewById(R.id.btnBlue);
        Button yellowButton = findViewById(R.id.btnYellow);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.startNewGame();
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.playerInput(0); // Representa el color rojo
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.playerInput(1); // Representa el color verde
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.playerInput(2); // Representa el color azul
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.playerInput(3); // Representa el color amarillo
            }
        });

        viewModel.getPatternLiveData().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> pattern) {
                // Actualiza la interfaz de usuario para mostrar la secuencia
                highlightSequence(pattern);
            }
        });
    }

    // Resalta la secuencia de colores en la interfaz de usuario
    private void highlightSequence(List<Integer> pattern) {
        final LinearLayout colorButtonsLayout = findViewById(R.id.colorButtonsLayout);

        final Handler handler = new Handler();
        for (int i = 0; i < pattern.size(); i++) {
            final int index = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int color = getColorForPattern(pattern.get(index));
                    Button buttonToHighlight = findButtonForPattern(pattern.get(index));

                    // Cambia el color de fondo del botón para resaltarlo
                    buttonToHighlight.setBackgroundColor(color);

                    // Espera un poco antes de restaurar el color original
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            buttonToHighlight.setBackgroundResource(R.drawable.button_background);
                        }
                    }, 500); // 500 milisegundos de espera
                }
            }, i * 1000); // 1000 milisegundos (1 segundo) de espera entre colores
        }
    }

    // Devuelve el color correspondiente al patrón dado
    private int getColorForPattern(int pattern) {
        int color;
        switch (pattern) {
            case 0:
                color = ContextCompat.getColor(this, R.color.highlightedRed);
                break;
            case 1:
                color = ContextCompat.getColor(this, R.color.highlightedGreen);
                break;
            case 2:
                color = ContextCompat.getColor(this, R.color.highlightedBlue);
                break;
            case 3:
                color = ContextCompat.getColor(this, R.color.highlightedYellow);
                break;
            default:
                color = ContextCompat.getColor(this, R.color.normalColor);
        }
        return color;
    }

    // Devuelve el botón correspondiente al patrón
    private Button findButtonForPattern(int pattern) {
        Button button;
        switch (pattern) {
            case 0:
                button = findViewById(R.id.btnRed);
                break;
            case 1:
                button = findViewById(R.id.btnGreen);
                break;
            case 2:
                button = findViewById(R.id.btnBlue);
                break;
            case 3:
                button = findViewById(R.id.btnYellow);
                break;
            default:
                button = findViewById(R.id.btnRed);
        }
        return button;
    }
}
