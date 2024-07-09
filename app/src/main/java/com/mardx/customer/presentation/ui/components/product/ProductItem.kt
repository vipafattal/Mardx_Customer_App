package com.mardx.customer.presentation.ui.components.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mardx.customer.models.Product
import com.mardx.customer.presentation.ui.preview.ProductPreviewItem
import com.mardx.customer.presentation.ui.theme.MardxCustomerAppTheme


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(280.dp)
            .testTag("item_${product.id}")
            .clickable {
                // Handle card tap here (e.g., navigate to details screen)
            },
        colors = cardColors(
            containerColor = Color.White, //Card background color
        ),
        elevation = cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            GlideImage(
                model = product.coverImageId,
                contentDescription = product.title,
                modifier = Modifier
                    .padding(8.dp)
                    .height(130.dp)
                    .fillMaxSize(),
            )
            Column(Modifier.padding(all = 9.dp)) {
                Text(text = product.title, maxLines = 2, minLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(8.dp)) // Add 8dp of space

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = product.price.toString())
                    Card(modifier = Modifier
                        .size(56.dp)
                        .clickable { }) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ShoppingCart,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview(@PreviewParameter(ProductPreviewItem::class) product: Product) {
    MardxCustomerAppTheme {
        ProductItem(
            product
        )
    }
}

