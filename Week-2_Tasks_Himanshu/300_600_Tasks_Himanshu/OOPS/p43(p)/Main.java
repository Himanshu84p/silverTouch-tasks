
import jcuda.Pointer;
import jcuda.Sizeof;
import jcuda.driver.CUdevice;
import jcuda.driver.CUdeviceptr;
import jcuda.driver.CUfunction;
import jcuda.driver.CUmodule;
import jcuda.driver.JCudaDriver;

public class Main {

    public static void main(String[] args) {
        int width = 1024;
        int height = 1024;
        int depth = 1024;

        
        float[] matrixA = new float[width * height];
        float[] matrixB = new float[height * depth];
        float[] matrixC = new float[width * depth];

        
        for (int i = 0; i < width * height; i++) {
            matrixA[i] = (float) Math.random();
        }
        for (int i = 0; i < height * depth; i++) {
            matrixB[i] = (float) Math.random();
        }

        
        CUdeviceptr devMatrixA = new CUdeviceptr();
        CUdeviceptr devMatrixB = new CUdeviceptr();
        CUdeviceptr devMatrixC = new CUdeviceptr();

        JCudaDriver.cuMemAlloc(devMatrixA, width * height * Sizeof.FLOAT);
        JCudaDriver.cuMemAlloc(devMatrixB, height * depth * Sizeof.FLOAT);
        JCudaDriver.cuMemAlloc(devMatrixC, width * depth * Sizeof.FLOAT);

        
        JCudaDriver.cuMemcpyHtoD(devMatrixA, Pointer.to(matrixA), width * height * Sizeof.FLOAT);
        JCudaDriver.cuMemcpyHtoD(devMatrixB, Pointer.to(matrixB), height * depth * Sizeof.FLOAT);

        
        CUmodule module = new CUmodule();
        JCudaDriver.cuModuleLoad(module, "matrix_multiply.ptx");

        
        CUfunction kernelFunction = JCudaDriver.cuModuleGetFunction(module, "matrix_multiply");

        
        int threadsPerBlock = 32;
        int blocksPerGrid = (width * depth + threadsPerBlock - 1) / threadsPerBlock;
        JCudaDriver.cuLaunchKernel(kernelFunction, blocksPerGrid, 1, 1, threadsPerBlock, 1, 1, devMatrixA, devMatrixB, devMatrixC, null);

        
        JCudaDriver.cuMemcpyDtoH(Pointer.to(matrixC), devMatrixC, width * depth * Sizeof.FLOAT);

        
        JCudaDriver.cuMemFree(devMatrixA);
        JCudaDriver.cuMemFree(devMatrixB);
        JCudaDriver.cuMemFree(devMatrixC);

        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < depth; j++) {
                System.out.printf("%.2f ", matrixC[i * depth + j]);
            }
            System.out.println();
        }
    }
}