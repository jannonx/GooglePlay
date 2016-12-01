package jannonx.com.googleplay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import jannonx.com.googleplay.R;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = new ImageView(this);
        mImageView.setImageResource(R.drawable.ic_launcher);
    }
}
