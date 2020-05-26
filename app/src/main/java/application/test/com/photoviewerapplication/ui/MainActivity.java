package application.test.com.photoviewerapplication.ui;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;

import application.test.com.photoviewerapplication.R;
import application.test.com.photoviewerapplication.BaseActivity;
import application.test.com.photoviewerapplication.BasePresenter;
import application.test.com.photoviewerapplication.ui.di.MainComponent;
import application.test.com.photoviewerapplication.ui.home.HomeFragment;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View {

    private MainComponent mainComponent;
    public static final String PHOTO_DIR = Environment.getExternalStorageDirectory().toString()
            + "/Photo";

    @Inject
    MainPresenter presenter;

    protected void inject(MainComponent mainComponent) {
        mainComponent.inject(this);
    }

    public MainComponent getMainComponent() {
        if (mainComponent == null) {
            mainComponent = MainComponent.Initializer
                    .init(this, getTestApplication().getApplicationComponent());
        }
        return mainComponent;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getMainComponent());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        copyAssets("photo", PHOTO_DIR);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, new HomeFragment(),
                        HomeFragment.class.getSimpleName())
                .commit();

        presenter.init();
    }

    private void copyAssets(String path, String outPath) {
        AssetManager assetManager = this.getAssets();
        String assets[];
        try {
            assets = assetManager.list(path);
            if (assets.length == 0) {
                copyFile(path, outPath);
            } else {
                String fullPath = outPath + "/" + path;
                File dir = new File(fullPath);
                if (!dir.exists())
                    if (!dir.mkdir()) Log.e("", "No create external directory: " + dir);
                for (String asset : assets) {
                    copyAssets(path + "/" + asset, outPath);
                }
            }
        } catch (IOException ex) {
            Log.e("", "I/O Exception", ex);
        }
    }

    private void copyFile(String filename, String outPath) {
        AssetManager assetManager = this.getAssets();

        InputStream in;
        OutputStream out;
        try {
            in = assetManager.open(filename);
            String newFileName = outPath + "/" + filename;
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}