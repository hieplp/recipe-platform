import React from "react";
import {Layout} from "~/components/layouts/Layout";
import {Title} from "~/components/ui/Title";
import {RecipeCardList} from "~/components/recipe/RecipeCardList";
import {Select} from "~/components/ui/Input";

export default function Recipes() {

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
            totalComments: 221
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

    const orderOptions = [
        {
            label: "Newest",
            value: 0
        },
        {
            label: "Oldest",
            value: 1
        },
        {
            label: "Most Popular",
            value: 2
        },
        {
            label: "Least Popular",
            value: 3
        }
    ];

    return (
        <Layout>
            <div className="relative w-full mt-3">
                <Title className="my-3">
                    Recipes
                </Title>

                <div className="flex justify-end w-full">
                    {/*Order*/}
                    <Select className="w-full md:max-w-xs"
                            labelClassName="font-bold"
                            options={orderOptions}/>
                </div>

                <RecipeCardList className="grid mt-5
                                           grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 2xl:grid-cols-4
                                           gap-5 md:gap-5"
                                recipes={recipes}/>

                <div className="w-full flex justify-center mt-10">
                    <button className="btn w-32 btn-outline btn-primary">Load More</button>
                </div>
            </div>
        </Layout>
    );
}