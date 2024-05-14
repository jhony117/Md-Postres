package com.example.mdpostres;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.transition.MaterialFadeThrough;
import com.google.android.material.transition.MaterialSharedAxis;

import java.util.ArrayList;
import java.util.List;


public class ProductsFragment extends Fragment implements OnClickListener {





    private final List<Product> selectedProducts = new ArrayList<>();


    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setExitTransition(new MaterialFadeThrough());

        setReenterTransition(new MaterialSharedAxis(MaterialSharedAxis.X, false));
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X, true));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);


    }

    @Override
    public  void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        ProductsAdapter adapter = new ProductsAdapter(getProducts(), this);
         RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.btnAddCar).setOnClickListener(v -> {
          //  NavHostFragment.findNavController(this).navigate(R.id.action_products_to_car);
            ProductsFragmentDirections.ActionProductsToCar action =
                    ProductsFragmentDirections.actionProductsToCar();
            action.setProducts(getProductsStr());

            NavHostFragment.findNavController(this).navigate(action);
        });




    }
     private List<Product> getProducts() {

        List<Product> products = new ArrayList<>();

         products.add(new Product("Classic",
                 "https://upload.wikimedia.org/wikipedia/commons/4/48/Dona_sencilla_mexicana.jpg"));
         products.add(new Product("Glazed",
                 "https://p0.pikist.com/photos/921/203/donut-mini-donut-small-round-cake-torus-" +
                         "glaze-fat-sugar-mixture-schokoplaettchen.jpg"));
         products.add(new Product("Chocolate",
                 "https://cdn.pixabay.com/photo/2017/04/13/23/35/dona-2228986__340.jpg"));
         products.add(new Product("Blue Berry",
                 "https://cdn.pixabay.com/photo/2017/11/22/00/18/donuts-2969490_1280.jpg"));
         products.add(new Product("Dark Chocolate",
                 "https://cdn.pixabay.com/photo/2017/04/12/21/18/dona-2225812_1280.jpg"));
         products.add(new Product("Strawberry",
                 "https://live.staticflickr.com/1338/1036945312_8e12c26d99_b.jpg"));
         products.add(new Product("Caramel",
                 "https://p0.pxfuel.com/preview/187/392/25/cute-sweet-tasty-delicious.jpg"));
         products.add(new Product("Wine",
                 "https://cdn.pixabay.com/photo/2017/08/11/21/46/donuts-2632878__340.jpg"));
         products.add(new Product("Penaut",
                 "https://cdn.pixabay.com/photo/2014/02/17/17/26/donuts-268388__340.jpg"));
         products.add(new Product("Apple",
                 "https://cdn.pixabay.com/photo/2020/10/12/15/58/donuts-5649218__340.jpg"));
         products.add(new Product("Special",
                 "https://live.staticflickr.com/3356/3282189390_4ba32754b6_b.jpg"));



         return null;
     }
     private String[] getProductsStr(){
        String[] productsStr = new String[selectedProducts.size()];
        int index = 0;
        for (Product product: selectedProducts){
            productsStr[index] = product.getName();
            index++;
        }
        return productsStr;
     }


    @Override
    public void onClick(Product product) {

        if(product.isSelected()){
            selectedProducts.add(product);
        } else {
            selectedProducts.remove(product);
        }

    }
}