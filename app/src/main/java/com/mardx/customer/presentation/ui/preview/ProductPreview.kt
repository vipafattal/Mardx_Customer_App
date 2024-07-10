package com.mardx.customer.presentation.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mardx.customer.models.Product

class ProductPreviewItem : PreviewParameterProvider<Product> {
    override val values: Sequence<Product> = sequenceOf(
        Product(
            id = "0",
            title = "Test Title 1",
            shortDescription = "Short Description",
            code = "0xf",
            coverImageId = "e2655db9-cd67-4d2b-8e19-5388da3719ce",
            isPreOrder = true,
            description = "",
            price = 500.0
        ),
        Product(
            id = "1",
            title = "Test Title 2 with more than two lines two see an example of a long text. Test Title 2 with more than two lines two see an example of a long text. ",
            shortDescription = "Short Description",
            code = "0xf",
            coverImageId = "e2655db9-cd67-4d2b-8e19-5388da3719ce",
            isPreOrder = true,
            description = "",
            price = 700.0
        ),
    )
}

class ProductPreviewLists : PreviewParameterProvider<List<Product>> {
    override val values: Sequence<List<Product>> = sequenceOf(
        listOf(
            Product(
                id = "0-0-0",
                title = "Test Title 1",
                shortDescription = "Short Description",
                code = "0xf",
                coverImageId = "e2655db9-cd67-4d2b-8e19-5388da3719ce",
                isPreOrder = true,
                description = "",
                price = 500.0
            ),
            Product(
                id = "1-1-1",
                title = "Test Title 2",
                shortDescription = "Short Description",
                code = "0xf",
                coverImageId = "e2655db9-cd67-4d2b-8e19-5388da3719ce",
                isPreOrder = true,
                description = "",
                price = 700.0
            ),
            Product(
                id = "2-2-2",
                title = "Test Title 3",
                shortDescription = "Short Description",
                code = "0xf",
                coverImageId = "e2655db9-cd67-4d2b-8e19-5388da3719ce",
                isPreOrder = true,
                description = "",
                price = 700.0
            ),
        ),
    )
}
