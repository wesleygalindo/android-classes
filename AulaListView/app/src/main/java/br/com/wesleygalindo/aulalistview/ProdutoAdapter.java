package br.com.wesleygalindo.aulalistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by wesley on 02/05/18.
 */

public class ProdutoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Produto> produtos;

    public ProdutoAdapter(Context context, ArrayList<Produto> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.produtos.get(position);
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
        View v = layoutInflater.inflate(R.layout.item_produto,null);

        // Produto na posição atual
        Produto produto = this.produtos.get(position);

        TextView textViewNome = v.findViewById(R.id.nome_produto);
        textViewNome.setText(produto.getNome());

        TextView textViewDesc = v.findViewById(R.id.desc_produto);
        textViewDesc.setText(produto.getDescricao());

        ImageView image = v.findViewById(R.id.img_produto);
        // Faz o download da imagem e adiciona ao imageView
        Picasso.get().load(produto.getUrlImg()).into(image);

        return v;
    }
}
