package com.mardx.customer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val title:String,
    @SerialName("cover_image_id")
    val coverImageId:String?,
    val description:String?,
    @SerialName("short_description")
    val shortDescription:String?,
    val code:String?,
    @SerialName("preorder")
    val isPreOrder:Boolean,
)

/*


class Product extends BaseProduct<ProductVariant> {
  final String title;
  final String? coverImageId;
  final String? shortDescription;
  final String? description, code;
  final List<ProductImage>? images;
  final bool isActive, isPreOrder;
  final ProductCategory? category;
  final Discount? _productDiscount;

  bool get hasVariants=> variants.isNotEmpty;
  String getCoverImagePublicUrl() =>
      coverImageId?.asCloudFileDir() ?? ApplicationTenantData.tenant.logo ?? "";

  const Product({
    required super.id,
    required super.categoryId,
    required super.price,
    required super.variants,
    required this.coverImageId,
    required Discount? productDiscount,
    required this.category,
    required this.shortDescription,
    required this.title,
    required this.images,
    required this.description,
    required this.code,
    required this.isActive,
    required this.isPreOrder,
  }) : _productDiscount = productDiscount;

  Product.fromMap(Map<String, dynamic> json)
      : images = (json["product_images"] as List).mapToImages(),
        description = json["description"],
        shortDescription = json["short_description"],
        coverImageId = json["cover_image_id"],
        code = json["code"],
        title = json['title'],
        isActive = json["active"] ?? true,
        isPreOrder = json["preorder"],
        _productDiscount = Discount.fromMapWithSource(
          (json["discounted_products"] as List<dynamic>).firstOrNull,
          Discount.PRODUCT_DISCOUNT_TYPE,
        ),
        category = json['category'] == null
            ? null
            : ProductCategory.fromMap(json['category']),
        super.fromMap(json) {
    variants.addAll(json['product_variations']
            ?.map((e) =>
                ProductVariant.fromMap(json['id'], json["price"].toDouble(), e))
            .toList(growable: false)
            ?.cast<ProductVariant>() ??
        []);
  }

  Discount? get discount => _productDiscount ?? category?.discount;
}



* */