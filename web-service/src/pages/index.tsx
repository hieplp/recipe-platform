import {Layout} from "~/components/layouts/Layout";
import React from "react";
import {Carousel} from "~/components/ui/Carousel";
import {CategorySlider} from "~/components/category/CategorySlider";
import {StyledLink} from "~/components/ui/Link";
import {useRouter} from "next/router";
import {RecipeCardList} from "~/components/recipe/RecipeCardList";

export default function Home() {

    const router = useRouter();

    const carouselItems = [
        {
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
        {

            image: "https://flowbite.com/docs/images/carousel/carousel-2.svg"
        }
    ];

    const categories = [
        {
            categoryId: "1",
            name: "Pasta",
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
        {
            categoryId: "2",
            name: "Pasta",
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        }
    ];

    const recipes = [
        {
            recipeId: "1",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            authorName: "John Doe",
            authorAvatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
        {
            recipeId: "2",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            authorName: "John Doe",
            authorAvatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
        {
            recipeId: "3",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            authorName: "John Doe",
            authorAvatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
        {
            recipeId: "4",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            authorName: "John Doe",
            authorAvatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
        {
            recipeId: "5",
            title: "Shoes!",
            image: "/photo1.jpeg",
            rating: 3,
            authorName: "John Doe",
            authorAvatar: "/avatar.jpg",
            time: "Yesterday",
            totalComments: 222
        },
    ]

    return (
        <Layout>

            <div className="relative w-full">
                <Carousel items={carouselItems} className="h-56 md:h-96 lg:h-[48rem]"/>
            </div>

            <div className="relative w-full mt-3">
                <StyledLink href="/categories"
                            className="text-xl font-bold
                                       my-6 hover:underline">
                    Popular Categories
                </StyledLink>

                <CategorySlider categories={categories}/>
            </div>

            <div className="relative w-full mt-3">
                <StyledLink href="/recipes"
                            className="text-xl font-bold
                                       my-6 hover:underline">
                    Super Delicious
                </StyledLink>
                <RecipeCardList recipes={recipes}
                                className="grid-cols-1 md:grid-cols-3 gap-5 md:gap-3"/>
                <div className="w-full flex justify-center">
                    <button className="btn w-32 btn-outline btn-primary"
                            onClick={() => void router.push("/recipes")}>
                        Show All
                    </button>
                </div>
            </div>
        </Layout>
    );
}
