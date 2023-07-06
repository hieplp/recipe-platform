import Image, {type ImageProps} from 'next/image';
import * as React from 'react';

import {clsx} from "clsx";

type NextImageProps = {
        useSkeleton?: boolean;
        imgClassName?: string;
        blurClassName?: string;
        alt: string;
    }
    & (
    | { width: string | number; height: string | number }
    | { layout: 'fill'; width?: string | number; height?: string | number }
    )
    & ImageProps;

/**
 *
 * @description Must set width using `w-` className
 * @param useSkeleton add background with pulse animation, don't use it if image is transparent
 * @param src
 * @param width
 * @param height
 * @param alt
 * @param className
 * @param imgClassName
 * @param blurClassName
 * @param rest
 */
export default function NextImage({
                                      useSkeleton = false,
                                      src,
                                      width,
                                      height,
                                      alt,
                                      className,
                                      imgClassName,
                                      blurClassName,
                                      ...rest
                                  }: NextImageProps) {
    const [status, setStatus] = React.useState(
        useSkeleton ? 'loading' : 'complete'
    );

    // Width
    const widthIsSet = className?.includes('w-') ?? false;
    width = Number(width);

    // Blur
    blurClassName = blurClassName == null ? 'filter blur bg-gray-300' : blurClassName;

    return (
        <figure style={!widthIsSet ? {width: `${width}px`} : undefined}
                className={className}>
            <Image src={src}
                   className={clsx(imgClassName, status === 'loading' && clsx('animate-pulse', blurClassName))}
                   width={width}
                   height={height}
                   alt={alt}
                   onLoadingComplete={() => setStatus('complete')}
                   {...rest}/>
        </figure>
    );
}