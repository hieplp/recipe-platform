import React, {useCallback, useState} from 'react';
import {useDropzone} from 'react-dropzone';
import {clsx} from "clsx";
import {PhotoIcon, XCircleIcon} from "@heroicons/react/24/outline";
import Image from "next/image";

interface ImageUploaderProps {
    onImageUpload: (imageDataUrl: string) => void;
    className?: string;
}

const ImageUploader: React.FC<ImageUploaderProps> = ({onImageUpload, className}) => {
    const onDrop = useCallback(
        (acceptedFiles: File[]) => {
            const file = acceptedFiles[0];
            const reader = new FileReader();

            reader.onload = (event) => {
                const imageDataUrl = event.target?.result as string;
                onImageUpload(imageDataUrl);
            };

            if (!file) {
                return;
            }

            if (file.type !== 'image/png' && file.type !== 'image/jpeg') {
                return;
            }

            reader.readAsDataURL(file);
        },
        [onImageUpload]
    );

    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop});

    return (
        <div
            {...getRootProps()}
            className={clsx(
                className,
                "flex justify-center items-center",
                "px-6 py-4 border-2 border-dashed rounded-md text-center cursor-pointer",
                isDragActive ? 'border-blue-500' : 'border-gray-300'
            )}
        >
            <input {...getInputProps()} />

            <div className="w-full space-y-2">
                <PhotoIcon className={clsx(
                    "w-20 h-20 mx-auto",
                    isDragActive ? 'text-blue-500' : 'text-gray-300')}
                />

                {isDragActive
                    ?
                    <p className="font-bold text-blue-500">Drop the image here</p>
                    :
                    <p className="font-bold text-gray-500">
                        Drag and drop an image here, or click to select an image
                    </p>
                }
                <p className="text-gray-300 text-center"> Supports: PNG, JPG </p>
            </div>
        </div>
    );
};

const SingleImageUpload = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement>
>(({
       ...props
   }, ref) => {
    //
    const [uploadedImage, setUploadedImage] = useState<string | null>(null);

    //
    const uploadImage = (imageDataUrl: string) => {
        setUploadedImage(imageDataUrl);
    }

    const removeImage = () => {
        setUploadedImage(null);
    }

    //
    return (
        <div ref={ref}
             className="relative flex justify-center items-center"
             {...props}
        >
            {/**/}
            {
                !uploadedImage &&
                <ImageUploader onImageUpload={uploadImage}
                               className="h-56 w-full"
                />
            }
            {/**/}
            {
                uploadedImage &&
                <>
                    <Image alt={""}
                           src={uploadedImage}
                           className="w-fit"
                           width={150} height={150}/>

                    <button className="absolute right-6 top-6
                                       text-gray-300 hover:text-error"
                            onClick={removeImage}>
                        <XCircleIcon className="w-6 h-6"/>
                    </button>
                </>
            }

        </div>

    );
});
SingleImageUpload.displayName = "SingleImageUpload";


export {ImageUploader, SingleImageUpload};
