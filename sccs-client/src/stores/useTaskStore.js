import { create } from 'zustand'

export const useTaskStore = create((set) => ({
    tasks: [],

    setTasks: (tasks) => set(() => ({tasks: tasks})),
}))
