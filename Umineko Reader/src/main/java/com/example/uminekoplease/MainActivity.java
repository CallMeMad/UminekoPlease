package com.example.uminekoplease;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/** Menu d'acceuil de selection de l'épisode et du jukebox */
public class MainActivity extends AppCompatActivity{

    /**Toggle permet de gérer en un bouton l'arrêt et la reprise du son*/
    private boolean toggle;
    /**Permet de jouer des assets mp3*/
    private Intent music;
    /**Musique et Background de l'épisode*/
    private int episode;
    /**SharedPreferenceImageFond*/
    public static final String SHARED_PREFS="1";

    /**DirectoryPath common*/
    public String dir;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**Background Image*/
    private ImageView bgImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
        setContentView(R.layout.activity_main);
        // gère les préférences
        getInit();
        // récupère les assets.
        getMenuAsset();
        // on commence à jouer de la musique
        startService(music);
        //Toggle Permet de gérer en 1 Bouton l'arrêt et la reprise
        toggle=true;
        //Crée le button de musique et le listener
        musicButton();

        for(int i=1;i<8;i++)
        {
            creerBouton("button"+i,"Episode "+i,String.valueOf(i));
        }
    }
    /**Fonction créant les boutons et leurs liens*/
    private void creerBouton(String name,String text, String value)
    {
        // on récupère l'ID en fonction du nom
        int resID= getResources().getIdentifier(name,"id",getPackageName());
        //On génère le bouton
        Button button = findViewById(resID);
        if(button!=null) {
            button.setText(text);
            button.setOnClickListener(view -> {
                Intent ChapListEp1 = new Intent(getApplicationContext(), ChapListEp1.class);
                stopService(music);
                toggle = false;
                ChapListEp1.putExtra("String", value);
                ChapListEp1.putExtra("Dir",dir);
                startActivity(ChapListEp1);
            });
        }
    }
    /**Fonction qui récupère le fond de l'application en fonction des préférences.*/
    private void getMenuAsset() {
        backgroundImage();
        //Récupère le son à jouer
        music = new Intent(getApplicationContext(), SoundService.class);
        //Different Son en fonction de l'épisode
        if(episode<5) {
            play(dir+"/common/bgm/home_sound1.mp3");
        }
        else {
            play(dir+"/bgm/home_sound2.mp3");
        }
    }

    /**Affiche l'image de fond*/
    private void backgroundImage() {
        bgImage = (ImageView)findViewById(R.id.fond);
        //Récupère le path de notre image de background
        Bitmap b = BitmapFactory.decodeFile(dir+"/common/background/"+episode+".jpg");
        bgImage.setImageBitmap(b);
    }

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    /**Fonction permettant de chercher une image dans la gallerie directement*/
    /**Permet de créer un bouton pour stop/play la musique*/
    private void musicButton()
    {
        //Crée le bouton de son
        ImageView imageView = (ImageView) findViewById(R.id.music);
        //Button to play/stop music
        imageView.setOnClickListener(v -> {
            if(!toggle)
            {
                toggle=true;
                imageView.setImageResource(R.drawable.musicplaying1);
                startService(music);
            }
            else
            {
                stopService(music);
                imageView.setImageResource(R.drawable.musicmuted1);
                toggle=false;
            }

        });
    }
    /**Fonction jouant de la musique*/
    private void play(String path)
    {
        music.putExtra("ID", path).putExtra("bgm",2);
    }
    /**Fonction qui récupère les préférences pour afficher le menu*/
    private void getInit() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        episode = Integer.parseInt(sharedPreferences.getString("save","5"));
        dir=Environment.getExternalStorageDirectory().getPath() +"/Android/obb/com.uminekomangareader";
    }
    //Destructor
    protected void onDestroy() {
        //stop service and stop music
        stopService(music);
        Log.i("MAINACT", "onDestroy!");
        super.onDestroy();
        //We don't finish because we want to be able to use back to go back here
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
