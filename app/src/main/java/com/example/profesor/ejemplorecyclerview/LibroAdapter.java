// https://www.codexpedia.com/android/defining-item-click-listener-for-recyclerview-in-android/
package com.example.profesor.ejemplorecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class LibroAdapter extends RecyclerView.Adapter <LibroAdapter.LibroViewHolder> {

    // Lista que contendra los libros
    private List<Libro> items;


    //(Listener) Para dotar de un mecanismo de listener al item
    private ItemClickListener clickListener;

    // (Listener)
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    // Clase interna que implementa el viewholder
    // La la clase implementa la interface View.OnClikListener

    public class LibroViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

       // views que contendran la información del item
        public TextView titulo;
        public TextView autor;
        public ImageView imagen;


        public LibroViewHolder(View itemView) {
            super(itemView);
            titulo =  itemView.findViewById(R.id.titulo);
            autor = itemView.findViewById(R.id.autor);
            imagen = itemView.findViewById(R.id.imagen);

            //(Listener)
            itemView.setOnClickListener(this);
        }

        // Sobrescribe método interface View.OnclickListener
        // para que pase parametros de la vista y posion al listener
        // que hemos creado
        @Override
        public void onClick(View view) {
            if(clickListener != null)
                clickListener.onClick(view,getAdapterPosition());

        }
    }  // Fin clase interna

    // Constructor
    LibroAdapter(List<Libro> items){
        this.items = items;
    }

    // Siempre tendremos que implementar estos 3 métodos abstractos  del RecyclerView.Adapter

    // Infla la view que renderiza el contenido del item en el recycler view
    @Override
    public LibroViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.libro_lista_item,parent,false);
        return new LibroViewHolder(v);
    }

    // Enlaza los views del item con sus correspondientes datos
   @Override
    public void onBindViewHolder( LibroViewHolder holder, int position) {
        holder.titulo.setText(items.get(position).getTitulo());
        holder.autor.setText(items.get(position).getAutor());
        holder.imagen.setImageResource(items.get(position).getImagen());
    }

    // Devuelve el tamaño de la lisa de datos
    @Override
    public int getItemCount() {
        return items.size();
    }

}
