package com.halodoc.medical.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.halodoc.medical.ChatEspecifico;
import com.halodoc.medical.ChatGrupal;
import com.halodoc.medical.R;
import com.halodoc.medical.modal.Usuario;
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_chats, null, false);
        return new ViewHolderChats(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChats.ViewHolderChats viewHolderChats, final int i) {
        viewHolderChats.tvChatUsuario.setText(listaUsuarios.get(i).getNombre());

        viewHolderChats.tvChatUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listaUsuarios.get(i).getNombre().equals("TODOS")) {
                    //Toast.makeText(v.getContext(), "CHAT GRUPAL", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), ChatGrupal.class);
                    intent.putExtra("usuario", usuario);
                    contexto.startActivity(intent);
                } else {
                    //Toast.makeText(v.getContext(), usuario.getNombre()+" , "+listaUsuarios.get(i).getNombre(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), ChatEspecifico.class);
                    intent.putExtra("usuarios", usuario);
                    intent.putExtra("usuariosdes", listaUsuarios.get(i));
                    intent.putExtra("usuario", listaUsuarios.get(i).getUsuario());
                    intent.putExtra("usuarioDestino", listaUsuarios.get(i).getNombre());
                    contexto.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class ViewHolderChats extends RecyclerView.ViewHolder {

        TextView tvChatUsuario;

        public ViewHolderChats(@NonNull View itemView) {
            super(itemView);

            tvChatUsuario = itemView.findViewById(R.id.tvChatUsuario);
        }
    }
}
