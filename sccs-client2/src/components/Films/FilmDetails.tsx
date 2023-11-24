"use client";

import { useCount } from "@/hooks/useCount";
import { FunctionComponent, useEffect, useState } from "react";
import { FilmInfo } from "./FilmInfo";

interface Props {
    title: string;
    genre: "comedy" | "horror";
    seasonsCount: number;
}

export const FilmDetails: FunctionComponent<Props> = ({
    title,
    genre,
    seasonsCount
}) => {
    let { count, decrement, increment } = useCount(0);
    useEffect(() => {
        console.log(`count: `, count);
        () => { };
    },
        [count]
    );
    return (
        <div>
            <FilmInfo
                title={title}
                genre={genre}
                seasonsCount={seasonsCount}
            />
            <div>
                <button onClick={decrement}>-</button>
                {count}
                <button onClick={increment}>+</button>
            </div>
        </div>
    );
};