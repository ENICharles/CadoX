package fr.eni.crt.cadox;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import fr.eni.crt.cadox.bo.Article;
import fr.eni.crt.cadox.databinding.ItemLineLayoutBinding;

public class ItemListAdapter extends ListAdapter<Article,ItemListAdapter.ItemViewHolder>
{
    private OnRecyclerViewEventListener listener;

    protected ItemListAdapter(@NonNull DiffUtil.ItemCallback<Article> diffCallback, @NonNull OnRecyclerViewEventListener eventCallback)
    {
        super(diffCallback);
        this.listener = eventCallback;
    }

    @NonNull
    @Override
    public ItemListAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater       = LayoutInflater.from(parent.getContext());
        ItemLineLayoutBinding itemBinding   = ItemLineLayoutBinding.inflate(layoutInflater,parent,false);

        return new ItemViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.ItemViewHolder holder, int position)
    {
        Article currentArticle = getItem(position);
        holder.bind(currentArticle);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        private final ItemLineLayoutBinding itemBinding;

        public ItemViewHolder(@NonNull ItemLineLayoutBinding itemBinding)
        {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Article article)
        {
            /* affichage des informations */
            itemBinding.setCurrentItem(article);

            //itemBinding.nomArticle.setText(article.getIntitule());
            itemBinding.getRoot().setOnClickListener(v -> {
                long position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    //déclencher l'appel à la callback
                    listener.OnItemClick(article);
                }
            });

            itemBinding.executePendingBindings();
        }
    }

    static class ArticleDiff extends DiffUtil.ItemCallback<Article>
    {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldArticle, @NonNull Article newArticle)
        {
            return oldArticle.getId() == newArticle.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Article oldArticle, @NonNull Article newArticle)
        {
            return oldArticle.equals(newArticle);
        }
    }
}
