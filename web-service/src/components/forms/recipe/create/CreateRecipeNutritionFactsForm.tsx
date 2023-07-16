import {WhiteDiv} from "~/components/ui/StyledDiv";
import {Input} from "~/components/ui/Input";
import {XCircleIcon} from "@heroicons/react/24/outline";
import React, {ChangeEvent} from "react";
import {uuid} from "~/utils/StringUtils";
import {LineBreak} from "~/components/ui/Line";

type NutritionType = {
    type: number;
    name: string;
    units: number[];
    isShown: boolean;
}

type NutritionUnit = {
    type: number;
    name: string;
    shortName: string;
}

type NutritionFacts = {
    id: string;
    type: number;
    unit?: number;
    value: number;
}


export function CreateRecipeNutritionFactsForm() {

    const [nutritionFacts, setNutritionFacts] = React.useState<NutritionFacts[]>([
        {
            id: uuid(),
            type: 0,
            unit: 0,
            value: 0
        }
    ]);

    const [nutritionTypes, setNutritionTypes] = React.useState<NutritionType[]>([
        {
            type: 0,
            name: "Calories",
            units: [0],
            isShown: false
        },
        {
            type: 1,
            name: "Carbohydrates",
            units: [1, 2],
            isShown: true
        },
        {
            type: 2,
            name: "Proteins",
            units: [1, 2],
            isShown: true
        },
        {
            type: 3,
            name: "Fats",
            units: [1, 2],
            isShown: true
        },
        {
            type: 4,
            name: "Vitamins",
            units: [3],
            isShown: true
        },
        {
            type: 5,
            name: "Minerals",
            units: [3],
            isShown: true
        },
        {
            type: 6,
            name: "Water",
            units: [4, 5],
            isShown: true
        },
        {
            type: 7,
            name: "Fiber",
            units: [1, 2],
            isShown: true
        },
        {
            type: 8,
            name: "Phytochemicals",
            units: [3],
            isShown: true
        },
        {
            type: 9,
            name: "Antioxidants",
            units: [3],
            isShown: true
        },
        {
            type: 10,
            name: "Omega-3 Fatty Acids",
            units: [1, 2],
            isShown: true
        },
        {
            type: 11,
            name: "Probiotics",
            units: [3],
            isShown: true
        },
        {
            type: 12,
            name: "Prebiotics",
            units: [3],
            isShown: true
        },
        {
            type: 13,
            name: "Nutrient Timing",
            units: [3],
            isShown: true
        },
        {
            type: 14,
            name: "Caloric Intake",
            units: [1, 2],
            isShown: true
        }
    ]);


    const nutritionUnits = [
        {
            type: 0,
            name: "kilocalories",
            shortName: "kcal"
        },
        {
            type: 1,
            name: "grams",
            shortName: "g"
        },
        {
            type: 2,
            name: "milligrams",
            shortName: "mg"
        },
        {
            type: 3,
            name: "varies",
            shortName: "varies"
        },
        {
            type: 4,
            name: "liters",
            shortName: "l"
        },
        {
            type: 5,
            name: "milliliters",
            shortName: "ml"
        }
    ] as NutritionUnit[];

    //
    const addNutritionFact = () => {
        const freeNutritionType = nutritionTypes.filter(nutritionType => nutritionType.isShown)[0];
        if (!freeNutritionType) {
            return;
        }

        // Add nutrition fact
        setNutritionFacts([
            ...nutritionFacts,
            {
                id: uuid(),
                type: freeNutritionType.type, // first type of the shown types
                unit: freeNutritionType.units[0], // first unit of the type
                value: 0
            }
        ]);

        // Hide nutrition type
        const newNutritionTypes = nutritionTypes.map((nutritionType) => {
            if (nutritionType.type === freeNutritionType.type) {
                return {
                    ...nutritionType,
                    isShown: false
                }
            }
            return nutritionType;
        });
        setNutritionTypes(newNutritionTypes);
    }

    const removeNutritionFact = (nutritionFact: NutritionFacts) => {
        // Remove nutrition fact
        const newNutritionFacts = nutritionFacts.filter((fact) => fact.id !== nutritionFact.id);
        setNutritionFacts(newNutritionFacts);

        // Show nutrition type
        const newNutritionTypes = nutritionTypes.map((nutritionType) => {
            if (nutritionType.type === nutritionFact.type) {
                return {
                    ...nutritionType,
                    isShown: true
                }
            }
            return nutritionType;
        });
        setNutritionTypes(newNutritionTypes);
    }

    const changeNutritionFactType = (event: ChangeEvent<HTMLSelectElement>, nutritionFactId: string, oldType: number) => {
        const newType = Number(event.target.value);

        // Update nutrition fact type
        const newNutritionFacts = nutritionFacts.map((nutritionFact) => {
            if (nutritionFact.id === nutritionFactId) {
                return {
                    ...nutritionFact,
                    type: newType
                }
            }
            return nutritionFact;
        });
        setNutritionFacts(newNutritionFacts);

        // Show/Hide nutrition types
        const newNutritionTypes = nutritionTypes.map((nutritionType) => {
            // Show old type
            if (nutritionType.type === oldType) {
                return {
                    ...nutritionType,
                    isShown: true
                }
            }

            // Hide new type
            if (nutritionType.type === newType) {
                return {
                    ...nutritionType,
                    isShown: false
                }
            }
            return nutritionType;
        });
        setNutritionTypes(newNutritionTypes);
    }

    return (
        <WhiteDiv>
            <div className="space-y-3">

                {
                    nutritionFacts.map((nutritionFact, index) => (
                        <div key={nutritionFact.id} className="space-y-3">
                            <div className="flex items-center space-x-3">
                                <div className="w-full grid grid-cols-12 gap-2">

                                    <select className="col-span-12 md:col-span-3 select select-bordered"
                                            onChange={(e) => changeNutritionFactType(e, nutritionFact.id, nutritionFact.type)}
                                    >
                                        {/**/}
                                        <option value={nutritionFact.type}>
                                            {nutritionTypes[nutritionFact.type]?.name}
                                        </option>

                                        {/**/}
                                        {
                                            nutritionTypes
                                                .filter(nutritionType => nutritionType.isShown)
                                                .map((nutritionType) => (
                                                    <option key={nutritionType.type}
                                                            value={nutritionType.type}>
                                                        {nutritionType.name}
                                                    </option>
                                                ))
                                        }
                                    </select>

                                    <Input className="col-span-6"
                                           type="number"
                                           placeholder="Value"/>

                                    <select className="col-span-6 md:col-span-3 select select-bordered">
                                        {
                                            nutritionTypes[nutritionFact.type]?.units
                                                .map((unit) => (
                                                    <option key={unit}
                                                            value={unit}>
                                                        {
                                                            nutritionUnits[unit]?.name
                                                        }
                                                    </option>
                                                ))
                                        }
                                    </select>
                                </div>

                                <button onClick={() => removeNutritionFact(nutritionFact)}>
                                    <XCircleIcon className="w-10 h-10 text-gray-300 hover:text-red-500"/>
                                </button>
                            </div>

                            <LineBreak/>
                        </div>
                    ))
                }
            </div>

            {/*Add Ingredient*/}
            {
                nutritionTypes.length !== nutritionFacts.length &&
                <div className="w-full flex justify-center mt-10">
                    <button className="btn w-36 normal-case btn-outline btn-primary"
                            onClick={addNutritionFact}>
                        Add Ingredient
                    </button>
                </div>
            }
        </WhiteDiv>
    )
}