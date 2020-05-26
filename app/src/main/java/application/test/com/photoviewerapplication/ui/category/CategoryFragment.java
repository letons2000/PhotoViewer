package application.test.com.photoviewerapplication.ui.category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import application.test.com.photoviewerapplication.R;
import application.test.com.photoviewerapplication.BaseFragment;
import application.test.com.photoviewerapplication.BasePresenter;

import application.test.com.photoviewerapplication.ui.category.di.CategoryComponent;
import application.test.com.photoviewerapplication.ui.home.model.PhotoData;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CategoryFragment extends BaseFragment implements CategoryContract.View,
        CategoryClickListener {
    public static final String TAG = "CategoryFragment";

    private CategoryComponent categoryComponent;
    private Unbinder unbinder;
    private List<PhotoData> mPhotoList;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.title)
    TextView title;

    @Inject
    CategoryPresenter presenter;

    protected void inject(CategoryComponent menuComponent) {
        menuComponent.inject(this);
    }

    public CategoryComponent getCategoryComponent() {
        if (categoryComponent == null) {
            categoryComponent = CategoryComponent.Initializer
                    .init(this, getTestApplication().getApplicationComponent());
        }
        return categoryComponent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getCategoryComponent());
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_category, container,false);
        unbinder = ButterKnife.bind(this, view);
        Bundle arguments = getArguments();
        mPhotoList = new ArrayList<>();
        if(arguments != null) {
            String catTitle = arguments.getString("CategoryTitle");
            fillupCategoryRecipeList(catTitle);
            getActivity().setTitle(catTitle);
            title.setText("Desired recipes from " + catTitle.toUpperCase() +" category");
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), mPhotoList,
                this);
        recyclerView.setAdapter(categoryAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                categoryAdapter.getFilter().filter(s);
                return false;
            }
        });

        presenter.init();
        return view;
    }

    private void fillupCategoryRecipeList(String categoryTitle){
        if(categoryTitle != null) {
            switch (categoryTitle) {
                case "halloween":
                    mPhotoList.add(new PhotoData("Avocado Pizza",
                            getString(R.string.default_description), R.drawable.avocado_pizza));
                    mPhotoList.add(new PhotoData("Thai Shrimp Curry",
                            getString(R.string.default_description), R.drawable.thai_shrimp));
                    break;
                case "vegan":
                    mPhotoList.add(new PhotoData("Vegetable Tempura",
                            getString(R.string.default_description), R.drawable.vegetable_tempura));
                    mPhotoList.add(new PhotoData("Beef with Mixed Vegetable",
                            getString(R.string.default_description), R.drawable.vegetable_beef));
                    break;
                case "bread":
                    mPhotoList.add(new PhotoData("Spring Roll",
                            getString(R.string.default_description), R.drawable.rolls));
                    break;
                case "healthy":
                    mPhotoList.add(new PhotoData("Age Dashi Tofu",
                            getString(R.string.default_description), R.drawable.japanese_tofu));
                    break;
                case "world":
                    mPhotoList.add(new PhotoData("Szechuan Chicken",
                            getString(R.string.default_description),  R.drawable.szechuan_chicken));
                    break;
                case "diet":
                    mPhotoList.add(new PhotoData("Szechuan Mixed Vegetable",
                            getString(R.string.default_description), R.drawable.szechuan_vegetable));
                    break;
                case "seasonal":
                    mPhotoList.add(new PhotoData("Deep Fried Chicken",
                            getString(R.string.default_description), R.drawable.japanese_fried_checken));
                    break;
                default:
                    break;
            }
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClicked(CategoryViewHolder holder, PhotoData photoData) {
        presenter.onClickRecipe(holder.mImage, photoData);
    }
}

