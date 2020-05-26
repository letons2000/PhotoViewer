package application.test.com.photoviewerapplication.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import application.test.com.photoviewerapplication.BaseFragment;
import application.test.com.photoviewerapplication.BasePresenter;
import application.test.com.photoviewerapplication.R;
import application.test.com.photoviewerapplication.ui.home.di.HomeComponent;
import application.test.com.photoviewerapplication.ui.home.model.PhotoData;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment implements HomeContract.View,
        HomeClickListener {
    public static final String TAG = "HomeFragment";

    private HomeComponent homeComponent;
    private Unbinder unbinder;
    private List<PhotoData> mPhotoList;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.scrollView)
    HorizontalScrollView scrollView;

    @BindView(R.id.bread_recipe)
    View bread_recipe;

    @BindView(R.id.healthy_recipe)
    View healthy_recipe;

    @BindView(R.id.seasonal_recipe)
    View seasonal_recipe;

    @BindView(R.id.world_recipe)
    View world_recipe;

    @BindView(R.id.diet_recipe)
    View diet_recipe;

    @BindView(R.id.halloween_recipe)
    View halloween_recipe;

    @BindView(R.id.vegan_recipe)
    View vegan_recipe;

    @Inject
    HomePresenter presenter;

    protected void inject(HomeComponent menuComponent) {
        menuComponent.inject(this);
    }

    public HomeComponent getCategoryComponent() {
        if (homeComponent == null) {
            homeComponent = HomeComponent.Initializer
                    .init(this, getTestApplication().getApplicationComponent());
        }
        return homeComponent;
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
        View view = (View) inflater.inflate(R.layout.fragment_home, container,false);
        getActivity().setTitle("All Recipes");
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mPhotoList = new ArrayList<>();
        mPhotoList.add(new PhotoData("Age Dashi Tofu",
                getString(R.string.default_description), R.drawable.japanese_tofu));
        mPhotoList.add(new PhotoData("Deep Fried Chicken",
                getString(R.string.default_description), R.drawable.japanese_fried_checken));
        mPhotoList.add(new PhotoData("Vegetable Tempura",
                getString(R.string.default_description), R.drawable.vegetable_tempura));
        mPhotoList.add(new PhotoData("Beef with Mixed Vegetable",
                getString(R.string.default_description), R.drawable.vegetable_beef));
        mPhotoList.add(new PhotoData("Szechuan Chicken",
                getString(R.string.default_description), R.drawable.szechuan_chicken));
        mPhotoList.add(new PhotoData("Szechuan Mixed Vegetable",
                getString(R.string.default_description), R.drawable.szechuan_vegetable));
        mPhotoList.add(new PhotoData("Green Curry Chicken",
                getString(R.string.default_description), R.drawable.green_curry_chicken));
        mPhotoList.add(new PhotoData("Green Curry Shrimp",
                getString(R.string.default_description), R.drawable.green_curry_shrimp));
        mPhotoList.add(new PhotoData("Avocado Pizza",
                getString(R.string.default_description), R.drawable.avocado_pizza));
        mPhotoList.add(new PhotoData("Thai Shrimp Curry",
                getString(R.string.default_description), R.drawable.thai_shrimp));
        mPhotoList.add(new PhotoData("Spring Roll",
                getString(R.string.default_description), R.drawable.rolls));


        HomeAdapter categoryAdapter = new HomeAdapter(getActivity(), mPhotoList,
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

        halloween_recipe.setOnClickListener(v -> presenter.onClickCategoryRecipe("halloween"));
        vegan_recipe.setOnClickListener(v ->presenter.onClickCategoryRecipe("vegan"));
        bread_recipe.setOnClickListener(v ->presenter.onClickCategoryRecipe("bread"));
        healthy_recipe.setOnClickListener(v ->presenter.onClickCategoryRecipe("healthy"));
        world_recipe.setOnClickListener(v ->presenter.onClickCategoryRecipe("world"));
        diet_recipe.setOnClickListener(v ->presenter.onClickCategoryRecipe("diet"));
        seasonal_recipe.setOnClickListener(v ->presenter.onClickCategoryRecipe("seasonal"));

        presenter.init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClicked(HomeViewHolder holder, PhotoData photoData) {
        presenter.onClickRecipe(holder.mImage, photoData);
    }
}

