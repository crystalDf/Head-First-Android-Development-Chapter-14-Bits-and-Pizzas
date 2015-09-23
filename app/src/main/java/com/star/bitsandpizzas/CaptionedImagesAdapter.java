package com.star.bitsandpizzas;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CaptionedImagesAdapter extends
        RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    private String[] mCaptions;
    private int[] mImageIds;

    public CaptionedImagesAdapter(String[] captions, int[] imageIds) {
        mCaptions = captions;
        mImageIds = imageIds;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            mCardView = cardView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.mCardView;

        TextView textView = (TextView) cardView.findViewById(R.id.info_text);
        textView.setText(mCaptions[position]);

        ImageView imageView = (ImageView) cardView.findViewById(R.id.info_image);
        imageView.setImageDrawable(cardView.getResources().getDrawable(mImageIds[position]));
        imageView.setContentDescription(mCaptions[position]);
    }

    @Override
    public int getItemCount() {
        return mCaptions.length;
    }
}
