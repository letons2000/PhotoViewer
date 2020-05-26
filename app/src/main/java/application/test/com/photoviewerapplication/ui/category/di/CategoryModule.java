package application.test.com.photoviewerapplication.ui.category.di;

import application.test.com.photoviewerapplication.dagger.fragment.FragmentModule;
import application.test.com.photoviewerapplication.dagger.fragment.FragmentScope;
import application.test.com.photoviewerapplication.ui.category.CategoryFragment;
import application.test.com.photoviewerapplication.ui.category.CategoryNavigator;
import application.test.com.photoviewerapplication.ui.category.CategoryPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class CategoryModule extends FragmentModule {

    private CategoryFragment categoryFragment;

    CategoryModule(final CategoryFragment categoryFragment) {
        super(categoryFragment);
        this.categoryFragment = categoryFragment;
    }

    @Provides
    @FragmentScope
    CategoryPresenter provideCategoryPresenter() {
        final CategoryPresenter categoryPresenter =
                new CategoryPresenter(categoryFragment,
                        new CategoryNavigator(categoryFragment));
        categoryFragment.getCategoryComponent().inject(categoryPresenter);
        return categoryPresenter;
    }
}
