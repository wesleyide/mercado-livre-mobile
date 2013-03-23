package com.mercadolivre.mobile.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mercadolivre.mobile.R;
import com.mercadolivre.mobile.utils.PhotoHandler;

public class CadastroActivity extends Activity {

	public final static String DEBUG_TAG = "CadastroActivity";
	private Camera camera;
	private int cameraId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		EditText titulo = (EditText) this.findViewById(R.id.anuncioTitulo);
		EditText descricao = (EditText) this.findViewById(R.id.anuncioDescricao);
		EditText preco = (EditText) this.findViewById(R.id.anuncioPreco);
		setContentView(R.layout.activity_cadastro);

		// Categorias
		String[] categorias = new String[] { "Academia e Esportes", "Educação", "Gastronomia", "Saúde", "Vestuário", "Outros" };
        Spinner spinner = (Spinner) findViewById(R.id.categorias);
        ArrayAdapter adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);

        // Câmera
        Button buttonTirarFoto = (Button) findViewById(R.id.anuncioTirarFotoButton);
        Button buttonSelecionaFoto = (Button) findViewById(R.id.anuncioSelecionaFotoButton);
        Button buttonSalvar = (Button) findViewById(R.id.anuncioSalvarButton);
        // do we have a camera?
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
        	Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG).show();
        } else {
        	cameraId = findFrontFacingCamera();
        	if (cameraId < 0) {
        		Toast.makeText(this, "No front facing camera found.", Toast.LENGTH_LONG).show();
        	} else {
                buttonTirarFoto.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NewApi")
					public void onClick(View v) {
                        //Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                    	try {
                    		camera = Camera.open(cameraId);
        				} catch (Exception e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
                    }
                });
        	}
        }
	}

	public void onClick(View view) {
		camera.takePicture(null, null, new PhotoHandler(getApplicationContext()));
	}

	@SuppressLint("NewApi")
	private int findFrontFacingCamera() {
		int cameraId = -1;
		// Search for the front facing camera
		/*
		int numberOfCameras = Camera.getNumberOfCameras();
		for (int i = 0; i < numberOfCameras; i++) {
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
				Log.d(DEBUG_TAG, "Camera found");
				cameraId = i;
				break;
			}
		}*/
		return 1;
	}

	@Override
	protected void onPause() {
		if (camera != null) {
			camera.release();
			camera = null;
		}
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}

}
