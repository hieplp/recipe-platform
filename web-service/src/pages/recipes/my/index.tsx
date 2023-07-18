import {Layout} from "~/components/layouts/Layout";
import {Title} from "~/components/ui/Title";
import React from "react";
import type Recipe from "~/types/Recipe";
import {RecipeCardMini} from "~/components/recipe/RecipeCard";
import {Input, Select} from "~/components/ui/Input";
import {PrimaryButton, SecondaryButton} from "~/components/ui/Button";

export default function MyRecipes() {

    const recipes = [
        {
            recipeId: "1",
            image: "/photo1.jpeg",
            title: "Lorem ipsum dolor sit amet",
        },
        {
            recipeId: "2",
            image: "/photo1.jpeg",
            title: "Lorem ipsum dolor sit amet",
        },
        {
            recipeId: "3",
            image: "/photo1.jpeg",
            title: "Lorem ipsum dolor sit amet",
        },
        {
            recipeId: "4",
            image: "/photo1.jpeg",
            title: "Lorem ipsum dolor sit amet",
        },
        {
            recipeId: "5",
            image: "/photo1.jpeg",
            title: "Lorem ipsum dolor sit amet",
        },
        {
            recipeId: "6",
            image: "/photo1.jpeg",
            title: "Lorem ipsum dolor sit amet",
        }
    ] as Recipe[];

    const statuses = [
        {
            label: "All",
            value: 0
        },
        {
            label: "Active",
            value: 1
        }
    ];

    const categories = [
        {
            label: "All",
            value: 0
        },
        {
            label: "Category 1",
            value: 1
        }
    ];

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
            <div className="relative w-full mt-3 space-y-3">
                <Title className="my-3">
                    My Recipes
                </Title>

                <div className="flex justify-end w-full space-x-2">

                    {/*Filter*/}
                    <div className="">
                        <div className="drawer-content">
                            <label htmlFor="filter-button" className="drawer-button btn btn-primary">
                                Filter
                            </label>
                        </div>

                        {/*Filter Content*/}
                        <div className="drawer drawer-end">
                            <input id="filter-button" type="checkbox" className="drawer-toggle"/>

                            <div className="drawer-side z-50">
                                <label htmlFor="filter-button" className="drawer-overlay"></label>

                                <div className="menu
                                                px-4
                                                pt-16 pb-10
                                                w-80 h-full
                                                flex flex-col
                                                bg-white
                                                space-y-3">

                                    <Title className="mb-5">
                                        Recipe Filter
                                    </Title>

                                    {/*Status*/}
                                    <Select label="Status"
                                            className="w-full"
                                            labelClassName="font-bold"
                                            options={statuses}/>

                                    {/*Category*/}
                                    <Select label="Category"
                                            className="w-full"
                                            labelClassName="font-bold"
                                            options={categories}/>

                                    {/*Order*/}
                                    <Select label="Order"
                                            className="w-full"
                                            labelClassName="font-bold"
                                            options={orderOptions}/>

                                    {/*Title*/}
                                    <Input label="Title"
                                           className="w-full flex-grow"
                                           placeholder="Search by title"
                                           labelClassName="font-bold"/>


                                    {/**/}
                                    <div className="mt-auto space-y-3">
                                        <PrimaryButton className="w-full normal-case">
                                            Filter
                                        </PrimaryButton>

                                        <SecondaryButton className="w-full normal-case">
                                            Reset
                                        </SecondaryButton>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <div className="grid
                                grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                    {
                        recipes.map((recipe) => (
                            <RecipeCardMini key={recipe.recipeId}
                                            {...recipe}/>
                        ))
                    }
                </div>

                <div className="w-full flex justify-center mt-10">
                    <button className="btn w-32 btn-outline btn-primary">Load More</button>
                </div>
            </div>
        </Layout>
    );
}