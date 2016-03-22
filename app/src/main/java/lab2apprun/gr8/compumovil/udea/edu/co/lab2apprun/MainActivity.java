package lab2apprun.gr8.compumovil.udea.edu.co.lab2apprun;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import layout.AppInfoFragment;
import layout.DevelopersInfoFragment;
import layout.Fragment1;

public class MainActivity extends AppCompatActivity {

    private String[] opciones;
    private DrawerLayout drawerLayout;
    private ListView listView;
    //Agregar un titulo que vamos a modificar del tipo charsecuence
    private CharSequence tituloSec;
    private CharSequence tituloApp;
    //Agregar el DrawerToggle nota : recordar que es el de la version 4 por temas de compatibilidad*
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicialiando mi el arreglo opciones
        opciones = new String[] {"Carreras",
                "Info. Aplicación",
                "Perfil Desarrolladores",
                "Cerrar sesión",
                "Salir"
                };
        drawerLayout= (DrawerLayout) findViewById(R.id.mainContainer) ;
        listView = (ListView) findViewById(R.id.leftMenu);

        //Seteamos el adapter del Listview que hace referencia a menuIzq
        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment1 fragment1 = null;
                AppInfoFragment fragment2 = null;
                DevelopersInfoFragment fragment3 = null;

                Log.d("Posicion", String.valueOf(position));


                FragmentManager fragmentManager = getSupportFragmentManager();

                switch (position) {
                    case 0:
                        fragment1 = new Fragment1();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frameContainer, fragment1)
                                .commit();
                        break;
                    case 1:
                        fragment2 = new AppInfoFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frameContainer, fragment2)
                                .commit();
                        break;
                    case 2:
                        fragment3 = new DevelopersInfoFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frameContainer, fragment3)
                                .commit();
                        break;
                    case 4:
                        closeApp();
                        break;
                    default:
                        fragment1 = new Fragment1();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frameContainer, fragment1)
                                .commit();
                        break;

                }

                //indicamos que se va comenzar una transaccion para colocar dentro del contenedor de frame el Fragment
                // que nosotros queremos. y usamos el metodo commit para actualizar el estado




                listView.setItemChecked(position, true);
                //Voy a mostrar cual fue el titulo que se selecciono cuando seleccionan una opcion
                tituloSec = opciones[position];
                getSupportActionBar().setTitle(tituloSec);
                drawerLayout.closeDrawer(listView);


            }
        });

        //Obtenemos la referencia de los textos
        tituloSec=getTitle();
        tituloApp=getTitle();
        //Colocar las acciones de abrir y cerrar el navigation drawer, vamos a utilizar un DrawerToggle
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.drawable.ic_action_action_subject,
                R.string.open,

                R.string.closed){
            //ctrl+O y sobreescribimos los metodos onDrawerClosed y onDrawerOpened


            /*
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ActivityCompat.invalidateOptionsMenu(NavDrawExamp.this);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ActivityCompat.invalidateOptionsMenu(NavDrawExamp.this);
            }*/
        };

        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void closeSession() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }


    private void closeApp(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        System.exit(0);
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

}
