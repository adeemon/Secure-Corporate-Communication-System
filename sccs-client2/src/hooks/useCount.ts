import { useCallback, useState } from "react"

export const useCount = (initialValue: number = 0) => {
    let [count, setCount] = useState(initialValue);

    const decrement = useCallback(() =>
        setCount(currentCount => --currentCount), []
    );
    const increment = useCallback(() =>
        setCount(currentCount => ++currentCount), []
    );

    return { count, decrement, increment };
}