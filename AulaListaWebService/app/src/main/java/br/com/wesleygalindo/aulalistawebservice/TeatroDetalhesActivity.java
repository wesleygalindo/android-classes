package br.com.wesleygalindo.aulalistawebservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TeatroDetalhesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teatro_detalhes);

        // Pega o objeto teatro enviado via intent pela activity anterior
        Teatro teatro = (Teatro) getIntent().getSerializableExtra("teatro");

        TextView textViewNome = findViewById(R.id.detalhes_nome_teatro);
        textViewNome.setText(teatro.getNome());

        TextView textViewDesc = findViewById(R.id.detalhes_desc_teatro);
        textViewDesc.setText(teatro.getDescricao());

        TextView textViewEndereco = findViewById(R.id.detalhes_endereco_teatro);
        textViewEndereco.setText(teatro.getLogradouro() + ", " + teatro.getBairro());

        TextView textViewTelefone = findViewById(R.id.detalhes_telefone_teatro);
        textViewTelefone.setText(teatro.getTelefone());

        ImageView image = findViewById(R.id.detalhes_img_teatro);

        String imgPath = teatro.getImgUrl();
        if(imgPath != null && !imgPath.isEmpty()) {
            Picasso.get().load(imgPath).into(image);
        }

    }
}
