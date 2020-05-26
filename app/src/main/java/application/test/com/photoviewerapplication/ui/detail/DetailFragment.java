package application.test.com.photoviewerapplication.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import application.test.com.photoviewerapplication.BaseFragment;
import application.test.com.photoviewerapplication.BasePresenter;
import application.test.com.photoviewerapplication.R;
import application.test.com.photoviewerapplication.ui.detail.di.DetailComponent;
import application.test.com.photoviewerapplication.ui.home.model.PhotoData;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailFragment extends BaseFragment implements DetailContract.View {
    public static final String TAG = "DetailFragment";
    public static final String PHOTO_EXTRA = "PhotoExtra";
    private DetailComponent detailComponent;
    private Unbinder unbinder;

    @Inject
    DetailPresenter presenter;

    @BindView(R.id.back)
    Button back;

    @BindView(R.id.image_detail)
    ImageView imageDetail;

    @BindView(R.id.title_detail)
    TextView titleDetail;

    @BindView(R.id.description_detail)
    TextView descriptionDetail;

    protected void inject(DetailComponent detailComponent) {
        detailComponent.inject(this);
    }

    public DetailComponent getDetailComponent() {
        if (detailComponent == null) {
            detailComponent = DetailComponent.Initializer
                    .init(this, getTestApplication().getApplicationComponent());
        }
        return detailComponent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getDetailComponent());
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        getActivity().setTitle("Recipe Details");
        Bundle arguments = getArguments();
        PhotoData photoData = (PhotoData) arguments.getSerializable(PHOTO_EXTRA);
        imageDetail.setImageResource(photoData.getPhotoImage());
        titleDetail.setText(photoData.getPhotoName());
        descriptionDetail.setText(photoData.getPhotoDescription());
        back.setOnClickListener(view1 -> getActivity().onBackPressed());
        presenter.init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
