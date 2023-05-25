package com.example.sqllite;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity /*ListActivity*/ {

    private ComentarioDataSource comentarioDataSource;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comentarioDataSource=new ComentarioDataSource(this);
        comentarioDataSource.open();
        List<Comentario> comentarios=comentarioDataSource.getAll();
        ArrayAdapter<Comentario> adapter=new ArrayAdapter<Comentario>(this, android.R.layout.simple_list_item_1,comentarios);
        lv=findViewById(R.id.list);
        //setListAdapter(adapter);
        lv.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        comentarioDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        comentarioDataSource.open();
    }

    public void onClick(View v){
        //ArrayAdapter<Comentario>adapter=(ArrayAdapter<Comentario>) getListAdapter();
        ArrayAdapter<Comentario>adapter=(ArrayAdapter<Comentario>) lv.getAdapter();
        Comentario comentario=null;
        switch (v.getId()){
            case R.id.button:
                String[] comentarios = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                comentario=comentarioDataSource.create(new Comentario(comentarios[nextInt]));
                adapter.add(comentario);
                break;
            case R.id.button2:
                if(adapter.getCount()>0){
                    comentario=(Comentario)adapter.getItem(0);
                    comentarioDataSource.delete(comentario);
                    adapter.remove(comentario);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }
}