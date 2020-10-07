package com.halodoc.medical.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.ActivityDokterDetail;
import com.halodoc.medical.ChatEspecifico;
import com.halodoc.medical.ChatGrupal;
import com.halodoc.medical.R;
import com.halodoc.medical.modal.Usuario;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterChats extends RecyclerView.Adapter<AdapterChats.ViewHolderChats> {

    Context contexto;
    Usuario usuario;
    ArrayList<Usuario> listaUsuarios;

    public AdapterChats(Context contexto, Usuario usuario, ArrayList<Usuario> listaUsuarios) {
        this.contexto = contexto;
        this.usuario = usuario;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public AdapterChats.ViewHolderChats onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_dokter, null, false);
        return new ViewHolderChats(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChats.ViewHolderChats viewHolderChats, final int i) {
        viewHolderChats.name.setText(listaUsuarios.get(i).getNombre());
        Picasso.get().load(listaUsuarios.get(i).getImage()).into(viewHolderChats.image);
        viewHolderChats.rating.setText(listaUsuarios.get(i).getSuka() + "%");
        viewHolderChats.experience.setText(listaUsuarios.get(i).getPengetahuan() + " %");

        viewHolderChats.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listaUsuarios.get(i).getNombre().equals("GROUP")) {
                    Intent intent = new Intent(v.getContext(), ChatGrupal.class);
                    intent.putExtra("usuario", usuario);
                    contexto.startActivity(intent);
                } else {
                    Intent intent = new Intent(v.getContext(), ChatEspecifico.class);
                    intent.putExtra("usuario", listaUsuarios.get(i).getUsuario());
                    intent.putExtra("usuarioDestino", listaUsuarios.get(i).getNombre());
                    contexto.startActivity(intent);
                }
            }
        });
        viewHolderChats.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, ActivityDokterDetail.class);
                intent.putExtra("image", listaUsuarios.get(i).getImage());
                intent.putExtra("name", listaUsuarios.get(i).getNombre());
                intent.putExtra("suka", listaUsuarios.get(i).getSuka());
                intent.putExtra("pengetahuan", listaUsuarios.get(i).getPengetahuan());
                intent.putExtra("temp_prak", listaUsuarios.get(i).getTemp_praktik());
                intent.putExtra("no_str", listaUsuarios.get(i).getNo_str());
                contexto.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class ViewHolderChats extends RecyclerView.ViewHolder {

        TextView name, rating, experience;
        ImageView image;
        Button detail;

        public ViewHolderChats(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            detail = itemView.findViewById(R.id.chat);
            rating = itemView.findViewById(R.id.rating);
            experience = itemView.findViewById(R.id.experience);
        }
    }
}
