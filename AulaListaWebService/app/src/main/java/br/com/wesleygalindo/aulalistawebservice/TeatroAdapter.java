package br.com.wesleygalindo.aulalistawebservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wesley on 15/05/18.
 */

public class TeatroAdapter extends BaseAdapter {

    private Context context;
    private List<Teatro> teatros;

    public TeatroAdapter(Context context) {
        this.context = context;
        this.teatros = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return teatros.size();
    }

    @Override
    public Object getItem(int position) {
        return this.teatros.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // Cria uma instancia do objeto LayoutInflater
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Transforma um XML em um objeto do tipo View
        View v = layoutInflater.inflate(R.layout.item_teatro,null);

        // Pega o Teatro na posição atual
        Teatro teatro = this.teatros.get(position);

        TextView textViewNome = v.findViewById(R.id.nome_teatro);
        textViewNome.setText(teatro.getNome());

        TextView textViewDesc = v.findViewById(R.id.desc_teatro);
        textViewDesc.setText(teatro.getDescricao());

        TextView textViewEndereco = v.findViewById(R.id.endereco_teatro);
        textViewEndereco.setText(teatro.getLogradouro() + ", " + teatro.getBairro());

        ImageView image = v.findViewById(R.id.img_teatro);

        String imgPath = teatro.getImgUrl();
        if(imgPath != null && !imgPath.isEmpty()) {
            // Faz o download da imagem e adiciona ao imageView
            Picasso.get().load(imgPath).into(image);
        }

        return v;
    }

    public void setTeatrosList(List<Teatro> teatros) {
        this.teatros = teatros;
    }
}
