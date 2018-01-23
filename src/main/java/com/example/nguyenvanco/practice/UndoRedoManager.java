package com.example.nguyenvanco.practice;

import java.util.Stack;

/**
 * Created by NGUYEN VAN CO on 1/19/2018.
 */

public class UndoRedoManager implements ICommand {
    Stack<ICommand> undoStack = new Stack<ICommand>();
    Stack<ICommand> redoStack = new Stack<ICommand>();

    public void PerformAction(ICommand command)
    {
        undoStack.push(command);
        System.out.println("nvc---00----undoStack:" +undoStack.size());
        command.Redo();
        redoStack.clear();
    }

    public void Undo()
    {
        if(CanNotUndo())
            return;
        ICommand cmd = undoStack.pop();
        cmd.Undo();
        redoStack.push(cmd);
        System.out.println("nvc-------undoStack:" +undoStack.size());
        System.out.println("nvc-------redoStack:" +redoStack.size());
    }
    public boolean CanNotUndo()
    {
        //return undoStack.c != 0;
        return  undoStack.isEmpty();
    }
    public boolean CannotRedo()
    {
        return redoStack.isEmpty();
    }
    public void Redo()
    {
        if (CannotRedo())
        {
            return;
        }
        ICommand cmd = redoStack.pop();
        cmd.Redo();
        undoStack.push(cmd);
    }

}

