package service;

public interface IMyStructure {
    INode findByCode(String code);
    INode findByRenderer(String renderer);
    int count();
}
