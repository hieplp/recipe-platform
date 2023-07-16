import {WhiteDiv} from "~/components/ui/StyledDiv";
import {uuid} from "~/utils/StringUtils";
import React from "react";
import {Textarea} from "~/components/ui/Input";
import {XCircleIcon} from "@heroicons/react/24/outline";

type Instruction = {
    id: string;
    description: string;
}

export function CreateRecipeInstructionsForm() {

    const [instructions, setInstructions] = React.useState<Instruction[]>([
        {
            id: uuid(),
            description: ""
        }
    ]);

    const addInstruction = () => {
        setInstructions([
            ...instructions,
            {
                id: uuid(),
                description: ""
            }
        ])
    }

    const removeInstruction = (instructionId: string) => {
        const newInstructions = instructions.filter((instruction) => instruction.id !== instructionId);
        setInstructions(newInstructions);
    }

    return (
        <>
            <WhiteDiv>
                <div className="space-y-2">
                    {
                        instructions.map((instruction, index) =>
                            <div key={instruction.id}
                                 className="flex items-center space-x-3">
                                <Textarea className="w-full"
                                          placeholder="This is an instruction"
                                />

                                {
                                    instructions.length > 1 &&
                                    <button onClick={() => removeInstruction(instruction.id)}>
                                        <XCircleIcon className="w-10 h-10 text-gray-300 hover:text-red-500"/>
                                    </button>
                                }
                            </div>
                        )
                    }

                    <div className="w-full flex justify-center">
                        <button className="btn w-36 normal-case btn-outline btn-primary"
                                onClick={addInstruction}>
                            Add Instruction
                        </button>
                    </div>
                </div>
            </WhiteDiv>
        </>
    )
}