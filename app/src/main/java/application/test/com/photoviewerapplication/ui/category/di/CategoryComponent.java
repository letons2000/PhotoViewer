package application.test.com.photoviewerapplication.ui.category.di;

import application.test.com.photoviewerapplication.dagger.application.ApplicationComponent;
import application.test.com.photoviewerapplication.dagger.fragment.FragmentModule;
import application.test.com.photoviewerapplication.dagger.fragment.FragmentScope;
import application.test.com.photoviewerapplication.ui.category.CategoryFragment;
import application.test.com.photoviewerapplication.ui.category.CategoryNavigator;
import application.test.com.photoviewerapplication.ui.category.CategoryPresenter;
import dagger.Component;

@FragmentScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                CategoryModule.class
        }
)
public interface CategoryComponent extends FragmentModule.Exposes {

    void inject(CategoryFragment fragment);

    void inject(CategoryPresenter presenter);

    void inject(CategoryNavigator navigator);

    final class Initializer {

        private Initializer() {
        }

        public static CategoryComponent init(final CategoryFragment categoryFragment,
                                             final ApplicationComponent applicationComponent) {
            return DaggerCategoryComponent.builder()
                    .applicationComponent(applicationComponent)
                    .categoryModule(new CategoryModule(categoryFragment))
                    .build();
        }
    }
}
