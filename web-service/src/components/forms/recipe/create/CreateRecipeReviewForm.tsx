import {WhiteDiv} from "~/components/ui/StyledDiv";
import {BorderedText} from "~/components/ui/Text";
import {InputLabel} from "~/components/ui/Input";
import React from "react";
import NextImage from "~/components/ui/NextImage";
import {uuid} from "~/utils/StringUtils";
import {_nutritionTypes, _nutritionUnits} from "~/components/forms/recipe/create/CreateRecipeNutritionFactsForm";

export function CreateRecipeReviewForm() {

    const checklists = [
        {
            id: uuid(),
            title: "Checklist 01",
            ingredients: [
                {
                    id: uuid(),
                    name: "Ingredient 01"
                },
                {
                    id: uuid(),
                    name: "Ingredient 02"
                },
                {
                    id: uuid(),
                    name: "Ingredient 03"
                }
            ]
        },
        {
            id: uuid(),
            title: "Checklist 02",
            ingredients: [
                {
                    id: uuid(),
                    name: "Ingredient 01"
                },
                {
                    id: uuid(),
                    name: "Ingredient 02"
                },
                {
                    id: uuid(),
                    name: "Ingredient 03"
                }
            ]
        }
    ];

    const nutritionFacts = [
        {
            id: uuid(),
            type: 0,
            unit: 0,
            value: 100
        },
        {
            id: uuid(),
            type: 1,
            unit: 1,
            value: 100
        },
        {
            id: uuid(),
            type: 0,
            unit: 0,
            value: 100
        },
        {
            id: uuid(),
            type: 0,
            unit: 0,
            value: 100
        },
        {
            id: uuid(),
            type: 0,
            unit: 0,
            value: 100
        }
    ];

    const instructions = [
        {
            id: uuid(),
            description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam."
        },
        {
            id: uuid(),
            description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam."
        },
        {
            id: uuid(),
            description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam."
                + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam."
        },
        {
            id: uuid(),
            description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam."
        },
        {
            id: uuid(),
            description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam."
        }
    ]

    return (
        <>
            <div className="divider">
                <p className="font-bold">
                    Basic Information
                </p>
            </div>
            <WhiteDiv>
                {/*Title*/}
                <BorderedText label="Title" labelClassName="font-bold">
                    ddd
                </BorderedText>

                {/*Description*/}
                <BorderedText label="Description" labelClassName="font-bold">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus adipisci alias aliquid
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus adipisci alias aliquid
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus adipisci alias aliquid
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus adipisci alias aliquid
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus adipisci alias aliquid
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus adipisci alias aliquid
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus adipisci alias aliquid
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus adipisci alias aliquid
                </BorderedText>

                {/*Image*/}
                <InputLabel className="font-bold">
                    Image
                </InputLabel>
                <NextImage alt=""
                           src="/newsletter.jpg"
                           className="rounded-xl w-full"
                           imgClassName="rounded-xl w-full"
                           width={1200}
                           height={800}
                />

                <div className="w-full grid grid-cols-1 md:grid-cols-3 gap-1">
                    {/*Preparation Time*/}
                    <BorderedText label="Preparation Time" labelClassName="font-bold">
                        30 minutes
                    </BorderedText>

                    {/*Cooking Time*/}
                    <BorderedText label="Cooking Time" labelClassName="font-bold">
                        30 minutes
                    </BorderedText>

                    {/*Servings*/}
                    <BorderedText label="Servings" labelClassName="font-bold">
                        4
                    </BorderedText>
                </div>
            </WhiteDiv>

            <div className="divider">
                <p className="font-bold">
                    Ingredients
                </p>
            </div>
            <WhiteDiv className="space-y-8">
                {
                    checklists.map((checklist, index) => (
                        <div key={checklist.id}>
                            <InputLabel className="font-bold">
                                {index + 1} . {checklist.title}
                            </InputLabel>
                            <div className="overflow-x-auto">
                                <table className="table table-zebra">
                                    {/* head */}
                                    <thead>
                                    <tr>
                                        <th>
                                            No.
                                        </th>
                                        <th>
                                            Ingredient
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {
                                        checklist.ingredients.map((ingredient, index) => (
                                            <tr key={ingredient.id}>
                                                <th>
                                                    {index + 1}
                                                </th>
                                                <td>
                                                    {ingredient.name}
                                                </td>
                                            </tr>
                                        ))
                                    }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    ))
                }
            </WhiteDiv>

            <div className="divider">
                <p className="font-bold">
                    Nutrition Facts
                </p>
            </div>
            <WhiteDiv>
                <table className="table table-zebra">
                    <thead>
                    <tr>
                        <th>
                            No.
                        </th>
                        <th>
                            Type
                        </th>
                        <th>
                            Value
                        </th>
                        <th>
                            Unit
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        nutritionFacts.map((nutritionFact, index) => (
                            <tr key={nutritionFact.id}>
                                <th>
                                    {index + 1}
                                </th>
                                <td>
                                    {_nutritionTypes[nutritionFact.type]?.name}
                                </td>
                                <td>
                                    {nutritionFact.value}
                                </td>
                                <td>
                                    {_nutritionUnits[nutritionFact.unit]?.name}
                                </td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </WhiteDiv>

            <div className="divider">
                <p className="font-bold">
                    Instructions
                </p>
            </div>
            <WhiteDiv className="space-y-8">
                <table className="table table-zebra">
                    <thead>
                    <tr>
                        <th>
                            No.
                        </th>
                        <th>
                            Description
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        instructions.map((instruction, index) => (
                            <tr key={instruction.id}>
                                <th>
                                    {index + 1}
                                </th>
                                <td>
                                    {instruction.description}
                                </td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </WhiteDiv>
        </>
    )
}